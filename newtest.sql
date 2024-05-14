
DROP TABLE IF EXISTS `echange`;
CREATE TABLE IF NOT EXISTS `echange` (
                                         `id` bigint NOT NULL AUTO_INCREMENT,
                                         `accepted_date` datetime(6) DEFAULT NULL,
                                         `creation_date` datetime(6) DEFAULT NULL,
                                         `refused_date` datetime(6) DEFAULT NULL,
                                         `state` tinyint DEFAULT NULL,
                                         `livre1_id` bigint DEFAULT NULL,
                                         `livre2_id` bigint DEFAULT NULL,
                                         `owner1_user_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
                                         `owner2_user_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
                                         PRIMARY KEY (`id`),
                                         KEY `FK22tct1xbov3jl8jcprqxercbt` (`livre1_id`),
                                         KEY `FKq1seinqmb74h162kg4ust38gi` (`livre2_id`),
                                         KEY `FKaolagb5ipangrxmn1dsrnwx8x` (`owner1_user_id`),
                                         KEY `FKd2e2i3vr5exo6yhsxmuq80hyr` (`owner2_user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `echange`
--

INSERT INTO `echange` (`id`, `accepted_date`, `creation_date`, `refused_date`, `state`, `livre1_id`, `livre2_id`, `owner1_user_id`, `owner2_user_id`) VALUES
                                                                                                                                                          (22, NULL, '2024-05-05 23:31:35.000000', '2024-05-05 23:54:40.000000', 1, NULL, 25, '93e96346-c964-4fa3-8f69-9102cc7817bf', '81c70593-553a-42e1-95e2-dd8e3d3b69be'),
                                                                                                                                                          (28, NULL, '2024-05-06 21:20:28.694000', '2024-05-07 00:53:33.911000', 1, NULL, 25, '93e96346-c964-4fa3-8f69-9102cc7817bf', '81c70593-553a-42e1-95e2-dd8e3d3b69be'),
                                                                                                                                                          (38, NULL, '2024-05-14 13:17:45.810000', NULL, 0, NULL, 18, '93e96346-c964-4fa3-8f69-9102cc7817bf', '35a86f92-dd2a-4fb5-be6b-981ad5a61d07'),
                                                                                                                                                          (39, NULL, '2024-05-14 13:18:25.921000', NULL, 0, NULL, 25, '93e96346-c964-4fa3-8f69-9102cc7817bf', '81c70593-553a-42e1-95e2-dd8e3d3b69be'),
                                                                                                                                                          (40, NULL, '2024-05-14 13:19:12.291000', NULL, 0, NULL, 37, '93e96346-c964-4fa3-8f69-9102cc7817bf', 'e8c6eda9-83d7-422a-9f79-a413f6839b0a');

-- --------------------------------------------------------

--
-- Structure de la table `livre`
--

DROP TABLE IF EXISTS `livre`;
CREATE TABLE IF NOT EXISTS `livre` (
                                       `id` bigint NOT NULL AUTO_INCREMENT,
                                       `auteur` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
                                       `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
                                       `titre` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
                                       `owner_user_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
                                       `disponible` bit(1) NOT NULL,
                                       PRIMARY KEY (`id`),
                                       KEY `FKms90ped6rxx37a1jlpo4f5cwg` (`owner_user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `livre`
--

INSERT INTO `livre` (`id`, `auteur`, `description`, `titre`, `owner_user_id`, `disponible`) VALUES
                                                                                                (18, 'Victor Hugo', 'Chef-d\'œuvre de la littérature française du XIXe siècle', 'Les Misérables', '35a86f92-dd2a-4fb5-be6b-981ad5a61d07', b'1'),
                                                                                                (20, 'J.K. Rowling', 'Série de romans fantastiques', 'Harry Potter', '35a86f92-dd2a-4fb5-be6b-981ad5a61d07', b'1'),
                                                                                                (21, 'Stephen King', 'Roman d\'horreur', 'Shining', '35a86f92-dd2a-4fb5-be6b-981ad5a61d07', b'1'),
                                                                                                (24, 'George Orwell', 'Roman dystopique', '1984', '35a86f92-dd2a-4fb5-be6b-981ad5a61d07', b'0'),
                                                                                                (25, 'J.R.R. Tolkien', 'Trilogie épique de fantasy', 'Le Seigneur des Anneaux', '81c70593-553a-42e1-95e2-dd8e3d3b69be', b'1'),
                                                                                                (27, 'F. Scott Fitzgerald', 'Roman de la littérature américaine', 'Gatsby le Magnifique', '81c70593-553a-42e1-95e2-dd8e3d3b69be', b'1'),
                                                                                                (28, 'Jane Austen', 'Roman classique de la littérature anglaise', 'Orgueil et Préjugés', '81c70593-553a-42e1-95e2-dd8e3d3b69be', b'1'),
                                                                                                (29, 'Agatha Christie', 'Roman policier', 'Dix Petits Nègres', '81c70593-553a-42e1-95e2-dd8e3d3b69be', b'1'),
                                                                                                (30, 'Gabriel García Márquez', 'Roman magique réaliste', 'Cent ans de solitude', '93e96346-c964-4fa3-8f69-9102cc7817bf', b'1'),
                                                                                                (31, 'Harper Lee', 'Roman classique américain', 'Ne tirez pas sur l\'oiseau moqueur', '93e96346-c964-4fa3-8f69-9102cc7817bf', b'1'),
                                                                                                (32, 'Fyodor Dostoevsky', 'Roman existentiel', 'Crime et Châtiment', '93e96346-c964-4fa3-8f69-9102cc7817bf', b'1'),
                                                                                                (33, 'Leo Tolstoy', 'Roman historique russe', 'Guerre et Paix', '93e96346-c964-4fa3-8f69-9102cc7817bf', b'1'),
                                                                                                (34, 'Mark Twain', 'Roman d\'aventure', 'Les Aventures de Tom Sawyer', '93e96346-c964-4fa3-8f69-9102cc7817bf', b'1'),
                                                                                                (35, 'Emily Brontë', 'Roman romantique', 'Les Hauts de Hurlevent', '81c70593-553a-42e1-95e2-dd8e3d3b69be', b'1'),
                                                                                                (37, 'Herman Melville', 'Roman philosophique', 'Moby Dick', 'e8c6eda9-83d7-422a-9f79-a413f6839b0a', b'1');

-- --------------------------------------------------------

--
-- Structure de la table `owner`
--

DROP TABLE IF EXISTS `owner`;
CREATE TABLE IF NOT EXISTS `owner` (
                                       `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
                                       `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
                                       `first_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
                                       `last_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
                                       `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
                                       `user_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                                       `possede` bit(1) NOT NULL,
                                       PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `owner`
--

INSERT INTO `owner` (`address`, `email`, `first_name`, `last_name`, `phone`, `user_id`, `possede`) VALUES
                                                                                                       ('123 Rue de Rivoli', 'amine.mellouli@example.com', 'Amine', 'Mellouli', '0123456789', '35a86f92-dd2a-4fb5-be6b-981ad5a61d07', b'1'),
                                                                                                       ('456 Baker Street', 'john.doe@example.com', 'John', 'Doe', '9876543210', '81c70593-553a-42e1-95e2-dd8e3d3b69be', b'1'),
                                                                                                       ('789 Elm Street', 'sarah.connor@example.com', 'Sarah', 'Connor', '5551234567', '93e96346-c964-4fa3-8f69-9102cc7817bf', b'1'),
                                                                                                       ('321 Main Street', 'terminator@example.com', 'Arnold', 'Schwarzenegger', '1234567890', 'e8c6eda9-83d7-422a-9f79-a413f6839b0a', b'1');

-- --------------------------------------------------------

--
-- Structure de la table `role`
--

DROP TABLE IF EXISTS `role`;
CREATE TABLE IF NOT EXISTS `role` (
                                      `role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                                      PRIMARY KEY (`role`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `role`
--

INSERT INTO `role` (`role`) VALUES
                                ('ADMIN'),
                                ('CONFIRMED'),
                                ('USER');

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
                                      `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                                      `can` bit(1) NOT NULL,
                                      `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
                                      `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
                                      PRIMARY KEY (`id`),
                                      UNIQUE KEY `UK_sb8bbouer5wak8vyiiy4pf2bx` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`id`, `can`, `password`, `username`) VALUES
                                                             ('35a86f92-dd2a-4fb5-be6b-981ad5a61d07', b'1', '$2a$10$0YkJXLD1cXUvM0.tSzInU.S/vRq1i2r.BzdCx4JGU3yES1Y0xZBIu', 'test'),
                                                             ('4a280bdc-4cdf-49a0-920c-9fb3b721eec5', b'0', '$2a$10$l1TR6F/ghUYJxPfZuZC7yeap/z3gXla0gl9tClZHB1Cv6RTEK.C8m', 'test2'),
                                                             ('81c70593-553a-42e1-95e2-dd8e3d3b69be', b'1', '$2a$10$/9fZuCBAUYVEHVMQD0TZy.1Hx9EqA2AgB7EjuPj02J0i/Gb.jgHTq', 'normal'),
                                                             ('93e96346-c964-4fa3-8f69-9102cc7817bf', b'1', '$2a$10$mDfbNsWQOxhr6Et4BEHphehURUaj0WLiBxZPqvKJ91bUIyDjX6vs2', 'admin'),
                                                             ('e8c6eda9-83d7-422a-9f79-a413f6839b0a', b'1', '$2a$10$zJcLh.s/U/QuyXqKUafSX.FAlEQsHw6LwBkGyAbEQGv0UvSSPp.z.', 'test1');

-- --------------------------------------------------------

--
-- Structure de la table `user_roles`
--

DROP TABLE IF EXISTS `user_roles`;
CREATE TABLE IF NOT EXISTS `user_roles` (
                                            `user_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                                            `roles_role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                                            KEY `FKdt1fca9hsi6b8t4x9l1ds0tuj` (`roles_role`),
                                            KEY `FK55itppkw3i07do3h7qoclqd4k` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `user_roles`
--

INSERT INTO `user_roles` (`user_id`, `roles_role`) VALUES
                                                       ('93e96346-c964-4fa3-8f69-9102cc7817bf', 'ADMIN'),
                                                       ('93e96346-c964-4fa3-8f69-9102cc7817bf', 'CONFIRMED'),
                                                       ('35a86f92-dd2a-4fb5-be6b-981ad5a61d07', 'CONFIRMED'),
                                                       ('81c70593-553a-42e1-95e2-dd8e3d3b69be', 'CONFIRMED'),
                                                       ('e8c6eda9-83d7-422a-9f79-a413f6839b0a', 'CONFIRMED'),
                                                       ('4a280bdc-4cdf-49a0-920c-9fb3b721eec5', 'USER');

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `echange`
--
ALTER TABLE `echange`
    ADD CONSTRAINT `FK22tct1xbov3jl8jcprqxercbt` FOREIGN KEY (`livre1_id`) REFERENCES `livre` (`id`),
    ADD CONSTRAINT `FKaolagb5ipangrxmn1dsrnwx8x` FOREIGN KEY (`owner1_user_id`) REFERENCES `owner` (`user_id`),
    ADD CONSTRAINT `FKd2e2i3vr5exo6yhsxmuq80hyr` FOREIGN KEY (`owner2_user_id`) REFERENCES `owner` (`user_id`),
    ADD CONSTRAINT `FKq1seinqmb74h162kg4ust38gi` FOREIGN KEY (`livre2_id`) REFERENCES `livre` (`id`);

--
-- Contraintes pour la table `livre`
--
ALTER TABLE `livre`
    ADD CONSTRAINT `FKms90ped6rxx37a1jlpo4f5cwg` FOREIGN KEY (`owner_user_id`) REFERENCES `owner` (`user_id`);

--
-- Contraintes pour la table `owner`
--
ALTER TABLE `owner`
    ADD CONSTRAINT `FKsi1e0ouv7mj9eg3ts4buj4wer` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Contraintes pour la table `user_roles`
--
ALTER TABLE `user_roles`
    ADD CONSTRAINT `FK55itppkw3i07do3h7qoclqd4k` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
    ADD CONSTRAINT `FKdt1fca9hsi6b8t4x9l1ds0tuj` FOREIGN KEY (`roles_role`) REFERENCES `role` (`role`);
