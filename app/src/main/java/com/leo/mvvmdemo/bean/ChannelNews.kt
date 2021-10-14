package com.leo.mvvmdemo.bean

data class ChannelNews(
    val channel: String,
    val list: List<NewsInfo>,
    val num: Int,
)

data class NewsInfo(
    val category: String,
    val content: String,
    val pic: String,
    val src: String,
    val time: String,
    val title: String,
    val url: String,
    val weburl: String,
)