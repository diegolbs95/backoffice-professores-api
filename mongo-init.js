db.createUser({
  user: "backoffice",
  pwd: "backoffice123",
  roles: [{ role: "readWrite", db: "backoffice_database" }]
});

db.createCollection("tb_professores");
db.tb_professores.createIndex({ "email": 1 }, { unique: true });

db.createCollection("tb_aulas");
db.tb_aulas.createIndex({ "professor_id": 1 });

db.createCollection("tb_tokens");
db.tb_tokens.createIndex({ "token": 1 }, { unique: true });