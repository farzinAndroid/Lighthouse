package pro.sparrow_team.lighthouse.model


data class ConfigurationResponse(
    val configuration: String,
    val id: Int,
    val location: String,
    val name: String,
)