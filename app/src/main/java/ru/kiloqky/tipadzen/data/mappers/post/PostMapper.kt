package ru.kiloqky.tipadzen.data.mappers.post

import com.google.gson.GsonBuilder
import org.json.JSONObject
import ru.kiloqky.tipadzen.data.db.entities.PostEntity
import ru.kiloqky.tipadzen.model.PostModel

object PostMapper {
    fun entityToModel(postEntity: PostEntity): PostModel =
        PostModel(postEntity.sha, postEntity.text)

    fun entityToModel(list: List<PostEntity>): List<PostModel> = list.map(::entityToModel)

    fun modelToEntity(postModel: PostModel) = PostEntity(postModel.sha, postModel.text)

    fun mapToJson(postEntity: PostEntity): String =
        GsonBuilder().setPrettyPrinting().create().toJson(postEntity)

    fun mapFromJson(strJson: String): PostEntity =
        JSONObject(strJson).let { PostEntity(it.getString("sha"), it.getString("text")) }

}