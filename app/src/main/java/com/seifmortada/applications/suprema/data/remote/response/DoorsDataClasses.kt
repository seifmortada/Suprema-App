package com.seifmortada.applications.suprema.data.remote.response

data class DoorCollectionResponse(
    val DoorCollection: DoorCollection,
    val Response: Response
)

data class DoorCollection(
    val total: String,
    val rows: List<Door>
)

data class Door(
    val id: String,
    val name: String,
    val status: String,
    val entry_device_id: EntryDevice,
    val open_duration: String,
    val open_timeout: String,
    val open_once: String,
    val door_group_id: DoorGroup,
    val relay_output_id: RelayOutput,
    val exit_button_input_id: ExitButtonInput,
    val unconditional_lock: String
)

data class EntryDevice(
    val id: String,
    val name: String,
    val input: Input,
    val event_filter: EventFilter,
    val voip: VoIP
)

data class Input(
    val supervised_inputs: String
)

data class EventFilter(
    val rows: String,
    val total: String
)

data class VoIP(
    val server: VoIPServer,
    val dtmf: Dtmf,
    val phonebook_list: String,
    val outbound: Outbound,
    val use_extension_num: String,
    val speakerVolume: String,
    val micVolume: String
)

data class VoIPServer(
    val address: String,
    val user_id: String,
    val password: String,
    val port: String,
    val autid: String,
    val regdur: String
)

data class Dtmf(
    val mode: String,
    val exit_button: String
)

data class Outbound(
    val use_prox_ser: String,
    val prox_ip_addrs: String,
    val prox_ser_port: String
)

data class DoorGroup(
    val id: String,
    val name: String
)

data class RelayOutput(
    val device_id: EntryDevice,
    val relay_index: String
)

data class ExitButtonInput(
    val device_id: EntryDevice,
    val input_index: String,
    val type: String,
    val simulated_unlock: String
)

