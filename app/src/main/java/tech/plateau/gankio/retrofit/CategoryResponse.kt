package tech.plateau.gankio.retrofit
import com.google.gson.annotations.SerializedName


/**
 * Created by sakura on 2017/12/3.
 */

data class CategoryResponse(
		@Transient var position: Int? = null,
		@SerializedName("_id") var id: String? = null, // 序号
		@SerializedName("createdAt") var createdAt: String? = null, // 创造时间
		@SerializedName("desc") var desc: String? = null, // 描述
		@SerializedName("images") var images: List<String?>? = null, // 图片
		@SerializedName("publishedAt") var publishedAt: String? = null, // 发布时间
		@SerializedName("source") var source: String? = null, // 来源
		@SerializedName("type") var type: String? = null, //前端
		@SerializedName("url") var url: String? = null, // 链接
		@SerializedName("used") var used: Boolean? = null, //
		@SerializedName("who") var who: String? = null // 发布者
)