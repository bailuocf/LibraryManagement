# 图书管理系统

一个基于 Java Swing 和 Maven 的桌面端图书管理系统原型。项目当前已完成主窗口、登录界面、侧边栏页面切换、图书本地文件数据层，以及支持 JPEG XL 的用户头像导入功能。

> 当前项目仍处于开发阶段。登录注册、图书页面、借阅归还等核心业务尚未全部完成，详见“开发状态”。

## 运行环境

- JDK 21
- Maven 3.8 或更高版本
- IntelliJ IDEA（推荐）

项目已经内置纯 Java JPEG XL 解码器，不需要另外安装 `djxl`、Homebrew 软件包或平台脚本。

## 使用 IntelliJ IDEA 运行

1. 使用 IDEA 打开 `LibraryManagement` 目录，或将 `LibraryManagement/pom.xml` 添加为 Maven 项目。
2. 打开 `文件 → 项目结构 → 项目`，将项目 SDK 设置为 JDK 21。
3. 在右侧 Maven 窗口点击“重新加载所有 Maven 项目”。
4. 打开 `com.yourname.library.Main`。
5. 点击 `main()` 方法左侧的绿色三角，选择“运行 Main.main()”。

第一次运行后，IDEA 顶部会生成 `Main` 运行配置，以后可以直接点击顶部绿色箭头启动。

## 使用终端运行

进入 Maven 项目目录：

```bash
cd LibraryManagement
```

编译并打包：

```bash
mvn clean package
```

运行生成的 JAR：

```bash
java -jar target/LibraryManagement-1.0-SNAPSHOT.jar
```

macOS 和 Linux 也可以运行：

```bash
./go.sh
```

`go.sh` 是命令行辅助脚本，不是运行项目的必要条件。Windows 用户可以使用 IDEA 或 Maven 命令运行。

## 用户头像

在“用户与设置”页面点击“选择头像”，可以导入：

- JPEG XL：`.jxl`
- PNG：`.png`
- JPEG：`.jpg`、`.jpeg`

导入的图片会统一转换并保存为：

```text
LibraryManagement/image/TouXiang.png
```

程序下次启动时会自动加载该头像。头像文件不存在、格式不支持或内容损坏时，界面会显示相应提示。

JPEG XL 使用项目内置的 [JXLatte](https://github.com/Traneptora/jxlatte) 进行解码，因此 Windows、macOS 和 Linux 都不需要安装额外解码器。目前支持静态 JPEG XL 图片，不支持动画 JPEG XL。

## 数据保存

图书数据通过 Java 对象序列化保存在：

```text
LibraryManagement/data/books.dat
```

现有数据层支持：

- 添加图书
- 根据书号删除图书
- 更新图书信息
- 查询全部图书
- 根据书号查询图书

目前这些数据操作尚未全部连接到 Swing 页面。

## 开发状态

| 模块 | 当前状态 |
| --- | --- |
| 主窗口与侧边栏 | 已完成基础界面和页面切换 |
| 登录 | 只有输入界面，尚未进行账号密码验证 |
| 注册 | 尚未实现 |
| 图书模型 | 已完成基础字段 |
| 图书本地数据层 | 已完成文件增删改查 |
| 首页 | 当前为占位页面 |
| 查看书籍 | 当前为占位页面 |
| 借阅与归还 | 当前为占位页面 |
| 用户资料设置 | 已有输入界面，资料保存尚未实现 |
| 用户头像 | 已支持 JXL、PNG、JPG/JPEG 的导入、预览和持久化 |
| 自动化测试 | 尚未添加 |

## 项目结构

```text
LibraryManagement-main/
├── README.md
└── LibraryManagement/
    ├── pom.xml
    ├── data/
    │   └── books.dat
    ├── image/
    ├── src/main/java/
    │   ├── com/yourname/library/
    │   │   ├── Main.java
    │   │   ├── dao/
    │   │   ├── model/
    │   │   ├── ui/
    │   │   └── util/
    │   └── com/traneptora/jxlatte/
    ├── src/main/resources/
    │   └── fonts/
    └── third_party/jxlatte/
        ├── LICENSE
        └── NOTICE.md
```

## 第三方组件

项目内置 JXLatte，用于解码静态 JPEG XL 图片：

- 项目地址：https://github.com/Traneptora/jxlatte
- 内置版本提交：`96501e9d8116c43d5982f8ab7b90c26b8051a8c9`
- 许可证：MIT
- 许可证文件：`LibraryManagement/third_party/jxlatte/LICENSE`

## 注意事项

- 请从 `LibraryManagement` 目录运行程序，确保相对路径下的数据和头像文件保存在正确位置。
- `target`、IDEA 配置和运行时头像已经通过 `.gitignore` 排除，不应提交到 GitHub。
- 当前登录按钮会直接进入主界面，仅用于界面调试，不代表登录功能已经完成。
