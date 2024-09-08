package pro.sparrow_team.lighthouse.model

data class ServerModel(
    val id:Int,
    val name:String,
    val picture:String,
)

val testList = listOf(
    ServerModel(
        0,
        "United States",
        ""
    ),
    ServerModel(
        1,
        "United Kingdom",
        ""
    ),
    ServerModel(
        2,
        "Canada",
        ""
    ),
    ServerModel(
        3,
        "Japan",
        ""
    ),
)