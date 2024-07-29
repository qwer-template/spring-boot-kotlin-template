package com.qwer.domain

import org.hibernate.annotations.GenericGenerator
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class User(
    @Id
    @GeneratedValue(generator = "uuidGenerator")
    @GenericGenerator(name = "uuidGenerator", strategy = "uuid2")
    @Column(length = 36)
    var id: String? = null,
    var username: String? = null,
    var password: String? = null,
    var createTime: Date? = null,
    var updateTime: Date? = null,
    var deleted: Boolean? = null
)