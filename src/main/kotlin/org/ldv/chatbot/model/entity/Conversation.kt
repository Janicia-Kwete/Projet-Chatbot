package org.ldv.chatbot.model.entity


import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
class Conversation(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    var id: Long? = null,

    @Column(nullable = false)
    var titre: String = "",

    @Column(nullable = false)
    var dateCreation: LocalDateTime = LocalDateTime.now(),

    // Association avec ModeleIA (Conversation est le maître de l'association)
    @ManyToOne
@JoinColumn(name = "modele_ia_id")
var modeleIA: ModeleIA? = null,

// Association avec Message
@OneToMany(mappedBy = "conversation", cascade = [CascadeType.REMOVE], orphanRemoval = true)
var messages: MutableList<Message> = mutableListOf(),
)
{

    lateinit var dateDerniereModification: LocalDateTime
}
