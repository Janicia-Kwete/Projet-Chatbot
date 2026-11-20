package org.ldv.chatbot.model.entity


import jakarta.persistence.*

@Entity
class FournisseurIA(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    var id: Long? = null,

    @Column(nullable = false)
    var nom: String,


    @Column(nullable = false)
    var actif: Boolean = true,

    // Association avec ModeleIA
    @OneToMany(mappedBy = "fournisseurIA", cascade = [CascadeType.REMOVE], orphanRemoval = true)
    var modelesIA: MutableList<ModeleIA> = mutableListOf()
)
{

}


