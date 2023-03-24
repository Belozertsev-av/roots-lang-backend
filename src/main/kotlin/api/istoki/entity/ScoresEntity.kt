package api.istoki.entity

import javax.persistence.*

@Entity
@Table(name = "scores")
class ScoresEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val scoresId: Int = 0,
    @ManyToOne
    @JoinColumn(name = "scores_user")
    val scoresUser: UserEntity,
    @ManyToOne
    @JoinColumn(name = "scores_language")
    val scoresLanguage: LanguageEntity,
    val scores: Int
)