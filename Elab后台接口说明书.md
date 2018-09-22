# REST 接口统一格式

**仅支持GET方法**

`_host_/rest/_method_
`

例：`localhost:8080/rest/listBanner`



# 统一返回格式:
```json
{
    resultCode:"00",  // 00为成功;99为失败;无特殊情况不设其他code
    resultMessage:"", // 成功时为空，失败时为失败原因（技术语言，不可打印到前端）
    result:{...}      // 具体的返回值，失败是为 null，以下所有result都是该字段的值
}
```

# 1. 轮播
method:`listBanner`

场景:首页轮播图，`name`对应banner文字，`image`对应图片，`url`对应"浏览作品"按钮

```json
result:
{
    list:
    [{
        name:"name",
        image:"/img/HJB766uvgG123jk",
        url:"/h5/bvghVIyguJHVUY",
        comment:"this is a comment"
    },
    ....]    
}
```


# 2. 大类
method:`listCategory1`

场景: 作品案例中的案例类别（一级分类），`textTitle/Cn/En`分别对应图片上的三款文字，
`image1`对应大类的图片（小图），`image2`对应点击"浏览页面"进入的落地页的背景图（大图），

```json
result:
{
    list:
    [{
        id:100,
        textTitle:"城市核心综合体",
        textCn:"天空森林城市",
        textEn:"Sky Forest City",
        image1:"/img/HJB766uvgG123jk",
        iamge2:"/img/HJB766uvgG123jk",
        comment:"this is a comment"
    },
    ....]
}
```

# 3. 小类
method:`listCategory2?parent= "一级分类的id" `

场景:一级分类下的二级分类，在大类落地页中以按钮列表形式展示,
按钮文字为`location+name`,链接为`url`

```json
result:
{
    parentId:100,
    list:
    [{
        name:"moumou",
        location:"重庆",
        url:"/h5/bvghVIyguJHVUY",
        comment:"this is a comment"
    },
    ....]
}
```

# 4.招聘
method:`listJob`

场景:加入我们，职位`postion`，人数`number`，公司`company`，地点`location`，
更新时间`updateTime`（时间戳），职位要求`description`

```json
result:
{
    list:
    [{
        postion:"高级产品经理",
        number:"2",
        company:"阿里巴巴",
        locaton:"杭州",
        updateTime:1537538017,
        description:"1.牛\n2.很牛\n3.超级牛"
    },
    ....]
}
```

