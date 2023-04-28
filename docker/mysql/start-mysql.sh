#创建数据存储目录
mkdir -p ./data
#设置忽略大小写
docker run -p 13306:3306 --name=mysql \
-v /Users/rainy/code/java/real-estate-backend/docker/mysql/data:/var/lib/mysql \
-e MYSQL_ROOT_PASSWORD=123456 \
-d --privileged=true --restart=unless-stopped mysql:8.0.31 --lower-case-table-names=2
