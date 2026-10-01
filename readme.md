ce code java est fait pour être éxécuter avec un compilateur java 8. 

pour une simplification et une meilleure compréhension, le mot de passe est en dur même si ca viole toutes kes règles de sécurité.

les requêtes sql fonctionnent pour mariadb.

il aurait pu avoir des classes abstraites ou des interfaces, mais j'ai volontairement choisi de faire la simplificité tout en respectant tant que c'est possible la philosophie de python. il aurait été possible de faire une approche en sql direct sans instancier d'objet et de tout faire que ce soit pour les concat et autre en sql. Mais je n'ai pas voulu faire cela, car de 1, cet exercise est pour une évaluation en java,
ensuite ca aurait sûrement un peu perdu des personnes qui ne savent pas trop comment on fait des requête sql plus compliqué. 

enfin, il aurait été possible avec les requêtes sql de créé des vues , mais aussi de créé des fonctions en sql . on ne peut pas avec mariadb mais on peut avec postGSQL créé des vrais nouveaux types ainsi que comment ils peuvent être validé. 

pour le moment, il reste à faire les insertitions, délétion et des update dans la base de donnée. éventuellement voir si on peut refactoriser le code.

les données dans formation.sql ont été inspiré par ma formation ou il y a ces cours ainsi que par ce que je connais déja. ces données restent fictives et ne sont pas forcément représentatifs du prix du marché ainsi que de leur durée.  il est à noter que si c'est on rajoute que c'est en hybride, il faudrait modifier le type de is_dist dans Boolean_formation en varchar , rajouter une autre colonne id  et faire des correspondances mais ca , apparramment selon les informations de l'évaluation, c'était pas à prévoir.


parmis les difficultés, je dirais que savoir ou se trouve le port par défaut de mariadb sur phpmyadmin, comment on créé une date manuellement à partir du scanner de l'utilisateur ainsi que comment on force java à utiliser le bon encodage pour que les données soient prises tout en respectant les accents et autres. mais ils ont été bien résolus. des fois nommer correctement en anglais. enfin penser à faire la javadoc.