package com.example.android.mvvmkotlinvk.data.repository

import com.example.android.mvvmkotlinvk.data.model.ModelGroup
import com.google.gson.JsonParser
import com.vk.sdk.api.*
import io.reactivex.Single
import javax.inject.Inject

class VkRepositoryImpl
@Inject constructor() : IVkRepository {

    private var request =
        VKApi.groups().get(VKParameters.from(VKApiConst.FIELDS, "members_count", VKApiConst.EXTENDED, 1))
    private var listGroups = arrayListOf<ModelGroup>()
    private var vkName: String? = null
    private var vkSubscription: String? = null
    private var vkAvatar: String? = null

    override fun getGroupsFromVk(): Single<List<ModelGroup>> {

        return Single.create<List<ModelGroup>> { subscriber ->
            request.executeWithListener(object : VKRequest.VKRequestListener() {

                override fun onComplete(response: VKResponse?) {
                    val jsonParser = JsonParser()
                    val parsedJson = jsonParser.parse(response!!.json.toString()).asJsonObject
                    val jsonArray = parsedJson.get("response").asJsonObject.getAsJsonArray("items")
                    for (je in jsonArray) {
                        if (je.asJsonObject.get("name") != null) {
                            vkName = je.asJsonObject.get("name").asString
                        }
                        if (je.asJsonObject.get("members_count") != null) {
                            vkSubscription = je.asJsonObject.get("members_count").asString
                        }
                        if (je.asJsonObject.get("photo_100") != null) {
                            vkAvatar = je.asJsonObject.get("photo_100").asString
                        }
                        listGroups.add(
                            ModelGroup(
                                vkName,
                                vkSubscription,
                                vkAvatar,
                                false
                            )
                        )
                    }
                    subscriber.onSuccess(listGroups)
                }
            })
        }
    }
}
