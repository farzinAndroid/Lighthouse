package pro.sparrow_team.lighthouse.model

data class Status(
    val advertise: Boolean = false,
    val currentVersion: Int = 0,
    val descriptions: Descriptions = Descriptions(),
    val email: String? = null,
    val functional: Boolean = false,
    val maxVersion: Int = 0,
    val minVersion: Int = 0,
    val payment: Boolean = false,
    val reject: Boolean = false,
    val url: String = ""
)
data class Descriptions(
    val maintenance: String? = null,
    val policies: String? = null,
    val reject: String? = null,
    val update: List<String> = emptyList()
)

val testStatus = Status(
    minVersion = 1,
    maxVersion = 1,
    functional = false,
    reject = true,
    currentVersion = 1,
    url = "https://play.google.com/store/apps/details?id=com.instagram.android&hl=en_US",
    email = "info@sparrow-team.pro",
    payment = false,
    advertise = false,
    descriptions = Descriptions(
        reject = "Thank you for your interest in our VPN service. Unfortunately, we have reached our maximum capacity and cannot accept new registrations at this time. We are working diligently to expand our services and will notify you as soon as we can accommodate new users.",
        update = listOf("hello"),
        policies = "At Lighthouse VPN, we prioritize your privacy. We collect minimal personal information, such as your email address for account creation and maintenance. Additionally, we collect usage data, including connection timestamps and server locations, to enhance our service and ensure its security. We implement robust security measures to protect your data and do not sell, trade, or rent your personal information to third parties. However, we may share information with trusted service providers to help us operate our service or comply with legal requirements.",
        maintenance = "We are currently maintaining our app. We'll be back soon. Thank you for your patience."
    )
)