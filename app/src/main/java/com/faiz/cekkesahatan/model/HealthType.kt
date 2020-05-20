package com.faiz.cekkesahatan.model

enum class HealthType {
    TS {
        override fun toString(): String {
            return "Tidak Sehat"
        }
    },
    AS {
        override fun toString(): String {
            return "Agak Sehat"
        }
    },
    S {
        override fun toString(): String {
            return "Sehat"
        }
    },
    SS {
        override fun toString(): String {
            return "Sangat Sehat"
        }
    }
}