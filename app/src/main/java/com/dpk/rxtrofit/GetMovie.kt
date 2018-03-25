package com.dpk.rxtrofit
import com.google.gson.annotations.SerializedName


/**
 * Created by AlphaDog on 2018/3/25.
 */
data class GetMovie<T>(
		@SerializedName("count") var count: Int?, //20
		@SerializedName("start") var start: Int?, //0
		@SerializedName("total") var total: Int?, //250
		@SerializedName("subjects") var subjects: T?,
		@SerializedName("title") var title: String? //豆瓣电影Top250
)

data class Subject(
		@SerializedName("rating") var rating: Rating?,
		@SerializedName("genres") var genres: List<String?>?,
		@SerializedName("title") var title: String?, //肖申克的救赎
		@SerializedName("casts") var casts: List<Cast?>?,
		@SerializedName("collect_count") var collectCount: Int?, //1247600
		@SerializedName("original_title") var originalTitle: String?, //The Shawshank Redemption
		@SerializedName("subtype") var subtype: String?, //movie
		@SerializedName("directors") var directors: List<Director?>?,
		@SerializedName("year") var year: String?, //1994
		@SerializedName("images") var images: Images?,
		@SerializedName("alt") var alt: String?, //https://movie.douban.com/subject/1292052/
		@SerializedName("id") var id: String? //1292052
)

data class Rating(
		@SerializedName("max") var max: Int?, //10
		@SerializedName("average") var average: Double?, //9.6
		@SerializedName("stars") var stars: String?, //50
		@SerializedName("min") var min: Int? //0
)

data class Director(
		@SerializedName("alt") var alt: String?, //https://movie.douban.com/celebrity/1047973/
		@SerializedName("avatars") var avatars: Avatars?,
		@SerializedName("name") var name: String?, //弗兰克·德拉邦特
		@SerializedName("id") var id: String? //1047973
)

data class Avatars(
		@SerializedName("small") var small: String?, //https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p230.webp
		@SerializedName("large") var large: String?, //https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p230.webp
		@SerializedName("medium") var medium: String? //https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p230.webp
)

data class Images(
		@SerializedName("small") var small: String?, //https://img3.doubanio.com/view/photo/s_ratio_poster/public/p480747492.webp
		@SerializedName("large") var large: String?, //https://img3.doubanio.com/view/photo/s_ratio_poster/public/p480747492.webp
		@SerializedName("medium") var medium: String? //https://img3.doubanio.com/view/photo/s_ratio_poster/public/p480747492.webp
)

data class Cast(
		@SerializedName("alt") var alt: String?, //https://movie.douban.com/celebrity/1054521/
		@SerializedName("avatars") var avatars: Avatars?,
		@SerializedName("name") var name: String?, //蒂姆·罗宾斯
		@SerializedName("id") var id: String? //1054521
)