package pl.ciesla.ryd.users.core.domain.model;

class User(
    var id: Long? = null,
    var username: String,
    var email: String,
    var password: String,
    var name: String? = null,
    var surname: String? = null
)
