-- Cours :
--     • id_cours
--     • nom => theme
--     • description => description_theme
--     • statut = > actif || supprime
--     • acces = > gratuit || payant
--     • prix
--     • []id_episode => ensemble/liste d’épisodes vidéo

INSERT INTO cours (id, nom, description, statut, acces, prix) 
VALUES ('372049f0-647c-11eb-ae93-0242ac130002', 'Docker', 'Docker... Une alternative aux VM !', 'actif', 'gratuit', 0);  
INSERT INTO cours_episodes_id VALUES ('372049f0-647c-11eb-ae93-0242ac130002', 'ddc2ced8-6489-11eb-ae93-0242ac130002');
INSERT INTO cours_episodes_id VALUES ('372049f0-647c-11eb-ae93-0242ac130002', 'c758595a-648a-11eb-ae93-0242ac130002');
INSERT INTO cours_episodes_id VALUES ('372049f0-647c-11eb-ae93-0242ac130002', '181003d4-648b-11eb-ae93-0242ac130002');

INSERT INTO cours (id, nom, description, statut, acces, prix) 
VALUES ('3d96e1a4-647c-11eb-ae93-0242ac130002', 'NosQL', 'Les NoSQL, une réponse au Big Data.', 'actif', 'gratuit', 0);
INSERT INTO cours_episodes_id VALUES ('3d96e1a4-647c-11eb-ae93-0242ac130002', '6ec2d90e-648b-11eb-ae93-0242ac130002');
INSERT INTO cours_episodes_id VALUES ('3d96e1a4-647c-11eb-ae93-0242ac130002', '53d2c884-648b-11eb-ae93-0242ac130002');

INSERT INTO cours (id, nom, description, statut, acces, prix) 
VALUES ('4346f850-647c-11eb-ae93-0242ac130002', 'UML', 'Un super cours sur la modélisation !', 'supprime', 'gratuit', 0);
INSERT INTO cours_episodes_id VALUES ('4346f850-647c-11eb-ae93-0242ac130002', 'd07eb0cc-648c-11eb-ae93-0242ac130002');
INSERT INTO cours_episodes_id VALUES ('4346f850-647c-11eb-ae93-0242ac130002', 'd8833806-648c-11eb-ae93-0242ac130002');
INSERT INTO cours_episodes_id VALUES ('4346f850-647c-11eb-ae93-0242ac130002', 'dfaa0858-648c-11eb-ae93-0242ac130002');
INSERT INTO cours_episodes_id VALUES ('4346f850-647c-11eb-ae93-0242ac130002', 'ac6226f0-648d-11eb-ae93-0242ac130002');
INSERT INTO cours_episodes_id VALUES ('4346f850-647c-11eb-ae93-0242ac130002', '19f832b8-648e-11eb-ae93-0242ac130002');
INSERT INTO cours_episodes_id VALUES ('4346f850-647c-11eb-ae93-0242ac130002', '24b04650-648e-11eb-ae93-0242ac130002');
INSERT INTO cours_episodes_id VALUES ('4346f850-647c-11eb-ae93-0242ac130002', '2d6a5326-648e-11eb-ae93-0242ac130002');
INSERT INTO cours_episodes_id VALUES ('4346f850-647c-11eb-ae93-0242ac130002', '171a8894-648d-11eb-ae93-0242ac130002');

INSERT INTO cours (id, nom, description, statut, acces, prix) 
VALUES ('4a9a0cfa-647c-11eb-ae93-0242ac130002', 'Java-JEE', 'Java JEE n`aura plus de secret pour vous.', 'supprime', 'payant', 50);
INSERT INTO cours_episodes_id VALUES ('4a9a0cfa-647c-11eb-ae93-0242ac130002', '90c72502-648e-11eb-ae93-0242ac130002');

INSERT INTO cours (id, nom, description, statut, acces, prix) 
VALUES ('4fc174ca-647c-11eb-ae93-0242ac130002', 'Java-Spring', 'JEE, Spring et Maven un aperçue des dépendaces de base.', 'actif', 'payant', 100);
INSERT INTO cours_episodes_id VALUES ('4fc174ca-647c-11eb-ae93-0242ac130002', '5acbf4be-64ac-11eb-ae93-0242ac130002');
INSERT INTO cours_episodes_id VALUES ('4fc174ca-647c-11eb-ae93-0242ac130002', '24d20960-64b7-11eb-ae93-0242ac130002');

-- Episode/Video : 
--     • id_episode
--     • concept => concept (du cours) qui est présenté
--     • href

-- Docker --
INSERT INTO episode (id, concept, href) 
VALUES ('ddc2ced8-6489-11eb-ae93-0242ac130002', 'Introduciton à Docker', 'https://www.youtube.com/watch?v=JSLpG_spOBM');
-- INSERT INTO cours_episodes_id VALUES ('372049f0-647c-11eb-ae93-0242ac130002', 'ddc2ced8-6489-11eb-ae93-0242ac130002');

INSERT INTO episode (id, concept, href) 
VALUES ('c758595a-648a-11eb-ae93-0242ac130002', 'Docker Images & Run Containers', 'https://www.youtube.com/watch?v=CcxbHkqzJuI');
-- INSERT INTO cours_episodes_id VALUES ('372049f0-647c-11eb-ae93-0242ac130002', 'c758595a-648a-11eb-ae93-0242ac130002');

INSERT INTO episode (id, concept, href) 
VALUES ('181003d4-648b-11eb-ae93-0242ac130002', 'Docker Compose', 'https://www.youtube.com/watch?v=dWcoIxRfs8Y');
-- INSERT INTO cours_episodes_id VALUES ('372049f0-647c-11eb-ae93-0242ac130002', '181003d4-648b-11eb-ae93-0242ac130002');

-- NoSQL --
INSERT INTO episode (id, concept, href) 
VALUES ('6ec2d90e-648b-11eb-ae93-0242ac130002', 'Contexte du Big Data', 'https://www.youtube.com/watch?v=an86433PT8Q');
-- INSERT INTO cours_episodes_id VALUES ('3d96e1a4-647c-11eb-ae93-0242ac130002', '6ec2d90e-648b-11eb-ae93-0242ac130002');

INSERT INTO episode (id, concept, href) 
VALUES ('53d2c884-648b-11eb-ae93-0242ac130002', 'Introduciton aux NoSQL', 'https://www.youtube.com/watch?v=MlJ6TNyTWXU');
-- INSERT INTO cours_episodes_id VALUES ('3d96e1a4-647c-11eb-ae93-0242ac130002', '53d2c884-648b-11eb-ae93-0242ac130002');

-- UML --
INSERT INTO episode (id, concept, href) 
VALUES ('d07eb0cc-648c-11eb-ae93-0242ac130002', 'Introduction à l`UML', 'https://www.youtube.com/watch?v=dJd6azZr9Kg');
-- INSERT INTO cours_episodes_id VALUES ('4346f850-647c-11eb-ae93-0242ac130002', 'd07eb0cc-648c-11eb-ae93-0242ac130002');

INSERT INTO episode (id, concept, href) 
VALUES ('d8833806-648c-11eb-ae93-0242ac130002', 'Cas d`utilisation - 1. Diagrammes de cas d`utilisation', 'https://www.youtube.com/watch?v=GC5BdRve38A');
-- INSERT INTO cours_episodes_id VALUES ('4346f850-647c-11eb-ae93-0242ac130002', 'd8833806-648c-11eb-ae93-0242ac130002');

INSERT INTO episode (id, concept, href) 
VALUES ('dfaa0858-648c-11eb-ae93-0242ac130002', 'Cas d`utilisation - 2. Scénarios détaillés et diagrammes de séquence', 'https://www.youtube.com/watch?v=1G0omjzh1OQ');
-- INSERT INTO cours_episodes_id VALUES ('4346f850-647c-11eb-ae93-0242ac130002', 'dfaa0858-648c-11eb-ae93-0242ac130002');

INSERT INTO episode (id, concept, href) 
VALUES ('ac6226f0-648d-11eb-ae93-0242ac130002', 'Diagrammes de classes - 1. Classes et associations', 'https://www.youtube.com/watch?v=8VMMu-vcF60');
-- INSERT INTO cours_episodes_id VALUES ('4346f850-647c-11eb-ae93-0242ac130002', 'ac6226f0-648d-11eb-ae93-0242ac130002');

INSERT INTO episode (id, concept, href) 
VALUES ('19f832b8-648e-11eb-ae93-0242ac130002', 'Diagrammes de classes - 2. Associations particulières, héritage', 'https://www.youtube.com/watch?v=nRqTXoiNUHk');
-- INSERT INTO cours_episodes_id VALUES ('4346f850-647c-11eb-ae93-0242ac130002', '19f832b8-648e-11eb-ae93-0242ac130002');

INSERT INTO episode (id, concept, href) 
VALUES ('24b04650-648e-11eb-ae93-0242ac130002', 'Diagrammes de classes - 3. Contraintes', 'https://www.youtube.com/watch?v=a3DWVNWg2Oo');
-- INSERT INTO cours_episodes_id VALUES ('4346f850-647c-11eb-ae93-0242ac130002', '24b04650-648e-11eb-ae93-0242ac130002');

INSERT INTO episode (id, concept, href) 
VALUES ('2d6a5326-648e-11eb-ae93-0242ac130002', 'Diagrammes de classes - 4. Opérations', 'https://www.youtube.com/watch?v=RxkarRkq10o');
-- INSERT INTO cours_episodes_id VALUES ('4346f850-647c-11eb-ae93-0242ac130002', '2d6a5326-648e-11eb-ae93-0242ac130002');

INSERT INTO episode (id, concept, href) 
VALUES ('171a8894-648d-11eb-ae93-0242ac130002', 'UML - Diagrammes de séquence (conception)', 'https://www.youtube.com/watch?v=fPm5NrvmXHc');
-- INSERT INTO cours_episodes_id VALUES ('4346f850-647c-11eb-ae93-0242ac130002', '171a8894-648d-11eb-ae93-0242ac130002');

-- Java-JEE --
INSERT INTO episode (id, concept, href) 
VALUES ('90c72502-648e-11eb-ae93-0242ac130002', 'Java vs Java EE: What`s The Differences?', 'https://www.youtube.com/watch?v=jmLfIZw2WWY');
-- INSERT INTO cours_episodes_id VALUES ('4a9a0cfa-647c-11eb-ae93-0242ac130002', '90c72502-648e-11eb-ae93-0242ac130002');

-- Java-Spring --
INSERT INTO episode (id, concept, href) 
VALUES ('5acbf4be-64ac-11eb-ae93-0242ac130002', 'Débuter avec Spring INTRO', 'https://www.youtube.com/watch?v=UUUtQr-KXOY');
-- INSERT INTO cours_episodes_id VALUES ('4fc174ca-647c-11eb-ae93-0242ac130002', '5acbf4be-64ac-11eb-ae93-0242ac130002');

INSERT INTO episode (id, concept, href) 
VALUES ('24d20960-64b7-11eb-ae93-0242ac130002', 'Spring rest', 'https://www.youtube.com/watch?v=VwD4CsJAYNo');
-- INSERT INTO cours_episodes_id VALUES ('4fc174ca-647c-11eb-ae93-0242ac130002', '24d20960-64b7-11eb-ae93-0242ac130002');