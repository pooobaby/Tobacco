### Tobacco

#### 项目简介

- 项目名称：Tobacco
- 项目功能：展示国内香烟品牌与企业，可以通过扫描条形码获取香烟信息
- 开始日期：2025-03-20
- 结束日期：2025-03-25
- 用时：40h

#### 项目流程

![](.\readme-images\flow_sheet.png)

#### 技术栈

- 核心框架
  - Jetpack Compose - 用于构建UI界面
  - Room - 本地数据库
  - Hilt - 依赖注入
- 架构模式
  - MVVM (Model-View-ViewModel)
- 其他技术
  - Kotlin - 主要开发语言
  - LiveData - 数据观察
  - Navigation - 页面导航
- 项目特点
  - 使用预打包数据库：从assets目录加载初始数据
  - 单例模式管理数据库实例
  - 模块化设计：将数据库、Repository、ViewModel等分离
  - 支持依赖注入：通过Hilt管理依赖关系
- 构建工具
  - Gradle - 构建工具
  - KSP (Kotlin Symbol Processing) - 用于代码生成

#### 主代码目录

- App.kt ：应用入口
- constant/ ：常量定义
  - Constant.kt ：常量类
- data/ ：数据层
  - local/ ：本地数据
    - dao/ ：数据访问对象
      - CigDao.kt ：香烟数据访问接口
      - FactoryDao.kt ：工厂数据访问接口
      - GroupDao.kt ：分组数据访问接口
    - database/ ：数据库
      - TobaccoDatabase.kt ：数据库类
    - entity/ ：实体类
    - repository/ ：数据仓库
- di/ ：依赖注入
  - CigModule.kt ：香烟模块依赖注入配置
- navigation/ ：导航
  - TobaccoNavigation.kt ：导航配置
- ui/ ：用户界面
  - MainActivity.kt ：主Activity
  - components/ ：UI组件
  - screen/ ：屏幕页面
  - theme/ ：主题
    - Theme.kt ：主题配置
- util/ ：工具类
  - SnackBarUtil.kt ：SnackBar工具类
- viewmodel/ ：视图模型
  - CigViewModel.kt ：香烟视图模型
  - FactoryViewModel.kt ：工厂视图模型
  - GroupViewModel.kt ：分组视图模型

#### 项目日志

- `2025-03-20`：
  - 开始项目，完成香烟数据的爬取，建立香烟数据库`tobacco.db`
  - 完成项目整体框架的搭建，数据库文件、Hilt、Repository、ViewModel等基础性文件建立完成
- `2025-03-21`：
  - 完成项目初始运行时导入预置数据库：**注意：**预置数据库与实体类的表结构必须完全一致，包括表名，字段是否为空，等等
  - 学习并实验了`Navigation`与`NavigationBar`、`NavigationBarItem`的配合，实验通过
- `2025-03-22`：
  - 完成`Groups`和`Factories`两个数据表的数据收集，因为企业简介需要整理，不能用爬虫，纯手工操作。
  - 修改项目的数据库框架，并测试通过
  - 开始搭建UI，初步实现HomeScreen的框架，开始实现资料库中的品牌打官司拼音排序的功能
- `2025-03-23`：
  - 完成资料库下的基本功能，界面还需要美化
  - 完成主页中的部分内容
- `2025-03-24`：
  - 今天的效率比较高，完成了除搜索和扫描的其它所有功能
  - 将界面的UI简单调整了一下，不想在这方面花费太多的时间
- `2025-03-25`
  - 增加了扫描功能，搜索功能不做了
  - 本项目结束
