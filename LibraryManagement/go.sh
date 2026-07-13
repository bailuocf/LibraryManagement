#!/bin/bash

# 清屏
clear

echo "=========================================="
echo "   📚 图书管理系统 - 启动脚本"
echo "=========================================="
echo ""

# 1. 清理并打包
echo "🔨 正在编译打包..."
mvn clean package -DskipTests

# 检查打包是否成功
if [ $? -eq 0 ]; then
    echo "✅ 打包成功！"
else
    echo "❌ 打包失败，请检查错误信息"
    exit 1
fi

echo ""

# 2. 添加执行权限
echo "🔓 设置执行权限..."
chmod +x target/LibraryManagement-1.0-SNAPSHOT.jar

echo ""

# 3. 运行程序
echo "🚀 启动程序..."
echo "=========================================="
echo "Done !"
java -jar target/LibraryManagement-1.0-SNAPSHOT.jar
