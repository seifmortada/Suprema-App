package com.seifmortada.applications.suprema.data.remote.response

import com.google.gson.annotations.SerializedName
import java.util.*

data class
ApiResponse(
    @SerializedName("User") val user: User,
    @SerializedName("Response") val response: Response
)

data class User(
    @SerializedName("user_id") val userId: String,
    @SerializedName("name") val name: String,
    @SerializedName("gender") val gender: String,
    @SerializedName("phone") val phone: String,
    @SerializedName("email") val email: String,
    @SerializedName("birthday") val birthday: Date,
    @SerializedName("photo_exists") val photoExists: Boolean,
    @SerializedName("pin") val pin: String,
    @SerializedName("pin_exists") val pinExists: Boolean,
    @SerializedName("login_id") val loginId: String,
    @SerializedName("password_exists") val passwordExists: Boolean,
    @SerializedName("updated_count") val updatedCount: Int,
    @SerializedName("last_modified") val lastModified: String,
    @SerializedName("start_datetime") val startDatetime: Date,
    @SerializedName("expiry_datetime") val expiryDatetime: Date,
    @SerializedName("security_level") val securityLevel: Int,
    @SerializedName("display_duration") val displayDuration: Int,
    @SerializedName("display_count") val displayCount: Int,
    @SerializedName("permission") val permission: Permission,
    @SerializedName("inherited") val inherited: Boolean,
    @SerializedName("user_group_id") val userGroupId: UserGroup,
    @SerializedName("disabled") val disabled: Boolean,
    @SerializedName("expired") val expired: Boolean,
    @SerializedName("department") val department: String,
    @SerializedName("user_title") val userTitle: String,
    @SerializedName("fingerprint_template_count") val fingerprintTemplateCount: Int,
    @SerializedName("face_count") val faceCount: Int,
    @SerializedName("visual_face_count") val visualFaceCount: Int,
    @SerializedName("cards") val cards: List<Card>,
    @SerializedName("card_count") val cardCount: Int,
    @SerializedName("access_groups") val accessGroups: List<AccessGroup>,
    @SerializedName("need_to_update_pw") val needToUpdatePw: Boolean
)

data class Permission(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("filter") val filter: Filter,
    @SerializedName("module") val module: Module,
    @SerializedName("device") val device: Device,
    @SerializedName("user") val user: User
)

data class Filter(
    @SerializedName("UserGroup") val userGroup: List<String>,
    @SerializedName("DeviceGroup") val deviceGroup: List<String>,
    @SerializedName("DoorGroup") val doorGroup: List<String>,
    @SerializedName("ElevatorGroup") val elevatorGroup: List<String>,
    @SerializedName("ZoneType") val zoneType: List<String>,
    @SerializedName("AccessGroup") val accessGroup: List<String>,
    @SerializedName("GraphicMapGroup") val graphicMapGroup: List<String>
)

data class Module(
    @SerializedName("Dashboard") val dashboard: PermissionAction,
    @SerializedName("User") val user: PermissionAction,
    @SerializedName("Device") val device: PermissionAction,
    @SerializedName("Door") val door: PermissionAction,
    @SerializedName("Elevator") val elevator: PermissionAction,
    @SerializedName("Zone") val zone: PermissionAction,
    @SerializedName("AccessControl") val accessControl: PermissionAction,
    @SerializedName("Monitoring") val monitoring: PermissionAction,
    @SerializedName("TA") val ta: PermissionAction,
    @SerializedName("Setting") val setting: PermissionAction,
    @SerializedName("Video") val video: PermissionAction,
    @SerializedName("Visitor") val visitor: PermissionAction,
    @SerializedName("Report") val report: PermissionAction
)

data class PermissionAction(
    @SerializedName("read") val read: Boolean,
    @SerializedName("write") val write: Boolean
)

data class Device(
    @SerializedName("id") val id: String
)

data class UserGroup(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String
)

data class Card(
    @SerializedName("id") val id: String,
    @SerializedName("card_id") val cardId: String,
    @SerializedName("display_card_id") val displayCardId: String,
    @SerializedName("status") val status: Int,
    @SerializedName("is_blocked") val isBlocked: Boolean,
    @SerializedName("is_assigned") val isAssigned: Boolean,
    @SerializedName("card_type") val cardType: CardType,
    @SerializedName("mobile_card") val mobileCard: Boolean,
    @SerializedName("issue_count") val issueCount: Int,
    @SerializedName("card_slot") val cardSlot: Int,
    @SerializedName("card_mask") val cardMask: Int,
    @SerializedName("wiegand_format_id") val wiegandFormatId: WiegandFormat
)

data class CardType(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("type") val type: Int
)

data class WiegandFormat(
    @SerializedName("id") val id: String
)

data class AccessGroup(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String
)

data class Response(
    @SerializedName("code") val code: Int,
    @SerializedName("link") val link: String,
    @SerializedName("message") val message: String
)
