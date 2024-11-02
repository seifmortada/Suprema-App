package com.seifmortada.applications.suprema.data.remote.request.add_user_dataclasses

import com.seifmortada.applications.suprema.data.remote.request.add_user_dataclasses.UserGroupId

data class AddUser(
    val access_groups: List<AddAccessGroup> = emptyList(),
    val department: String="",
    val disabled: String="",
    val email: String,
    val expiry_datetime: String="2030-12-31T23:59:00.00Z",
    val login_id: String,
    val name: String,
    val password: String,
    val permission: AddPermission = AddPermission("1"),
    val phone: String="",
    val photo: String="",
    val start_datetime: String="2001-01-01T00:00:00.00Z",
    val user_group_id: UserGroupId = UserGroupId("1"),
    val user_id: String,
    val user_ip: String="",
    val user_title: String=""
)