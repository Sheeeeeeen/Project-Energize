package ph.developer.projectenergize

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform