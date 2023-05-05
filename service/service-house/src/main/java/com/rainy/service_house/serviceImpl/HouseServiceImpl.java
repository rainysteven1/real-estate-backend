package com.rainy.service_house.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rainy.commonutils.constants.ResultCode;
import com.rainy.commonutils.exception.CustomException;
import com.rainy.service_base.service.MinioService;
import com.rainy.service_house.entity.*;
import com.rainy.service_house.mapper.HouseFilesMapper;
import com.rainy.service_house.mapper.HouseMapper;
import com.rainy.service_house.mapper.HouseUserMapper;
import com.rainy.service_house.service.HouseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author rainy
 * @since 2023-05-04
 */
@Service
public class HouseServiceImpl extends ServiceImpl<HouseMapper, House> implements HouseService {

    @Value("${minio.house-folder}")
    private String folder;

    @Autowired
    private HouseMapper houseMapper;

    @Autowired
    private HouseUserMapper houseUserMapper;

    @Autowired
    private HouseFilesMapper houseFilesMapper;

    @Autowired
    private MinioService minioService;

    private void profileValidate(House house) {
        if (StringUtils.isBlank(house.getTitle())) {
            throw new CustomException(ResultCode.ERROR_HOUSE_PROFILE, "标题内容不能为空");
        }
    }

    private String uploadFile(MultipartFile file, String id, String type) throws IOException {
        String temp = String.format("%s/%s/%s", folder, id, type);
        String fileName = Objects.requireNonNull(file.getOriginalFilename()).split("\\.")[0];
        InputStream inputStream = file.getInputStream();
        String contentType = file.getContentType();
        return minioService.uploadFile(inputStream, fileName, contentType, temp);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void houseAdd(House house, List<MultipartFile> houseImages,
                         List<MultipartFile> floorPlainImages, String userId) {
        profileValidate(house);
        house.setPreview("");
        houseMapper.insert(house);
        QueryWrapper<House> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title", house.getTitle()).eq("deleted", false);
        Long id = houseMapper.selectOne(queryWrapper).getId();
        List<HouseFiles> houseFilesList = new ArrayList<>();
        try {
            if (houseImages.size() != 0) {
                for (MultipartFile file : houseImages) {
                    HouseFiles houseFiles = new HouseFiles();
                    houseFiles.setHouseId(id).setType(true).setDeleted(false)
                            .setImageURL(uploadFile(file, String.valueOf(id), "houseImages"));
                    houseFilesList.add(houseFiles);
                }
            }
            if (floorPlainImages.size() != 0) {
                for (MultipartFile file : floorPlainImages) {
                    HouseFiles houseFiles = new HouseFiles();
                    houseFiles.setHouseId(id).setType(false).setDeleted(false)
                            .setImageURL(uploadFile(file, String.valueOf(id), "floorPlainImages"));
                    houseFilesList.add(houseFiles);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new CustomException(ResultCode.ERROR_HOUSE_PROFILE, "文件上传失败");
        }
        house.setPreview(houseFilesList.get(0).getImageURL());
        houseMapper.updateById(house);
        HouseUser houseUser = new HouseUser();
        houseUser.setHouseId(id).setUserId(userId).setType(true);
        houseUserMapper.insert(houseUser);
        houseFilesMapper.insertBatchSomeColumn(houseFilesList);
    }

    @Override
    public IPage<HouseListVO> houseList(HouseQuery houseQuery) {
        Page<HouseListVO> page = new Page<>(houseQuery.getPageSize(), houseQuery.getPageSize());
        System.out.println(houseMapper.listByHouseQuery(page, houseQuery).getRecords());
        return houseMapper.listByHouseQuery(page, houseQuery);
    }

    @Override
    public House houseList(Long id) {
        return houseMapper.listById(id);
    }

    @Override
    public IPage<HouseListVO> ownList(HouseUserQuery houseUserQuery) {
        Page<HouseListVO> page = new Page<>(houseUserQuery.getPageNum(), houseUserQuery.getPageSize());
        return houseMapper.listByHouseUserQuery(page, houseUserQuery);
    }

    @Override
    public Double updateRating(Long id, Double rating) {
        QueryWrapper<House> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id)
                .eq("state", true)
                .eq("deleted", false);
        House house = houseMapper.selectOne(wrapper);
        if (house == null) {
            throw new CustomException(ResultCode.ERROR_HOUSE_PROFILE, "该房屋不存在");
        }
        Double oldRating = house.getRating();
        Double newRating = oldRating.equals(0D) ? rating : Math.min((oldRating + rating) / 2, 5);
        UpdateWrapper<House> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", id).set("rating", newRating);
        houseMapper.update(null, updateWrapper);
        return newRating;
    }
}
