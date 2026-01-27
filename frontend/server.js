import express from "express";
import path from "path";

const app = express();
const __dirname = path.resolve();

app.use(express.static("html"));
app.use("/js", express.static("js"));

app.get("/", (_, res) =>
  res.sendFile(__dirname + "/html/index.html")
);

app.get("/register", (_, res) =>
  res.sendFile(__dirname + "/html/register.html")
);

app.get("/login", (_, res) =>
  res.sendFile(__dirname + "/html/login.html")
);

app.get("/verify-user", (_, res) =>
  res.sendFile(__dirname + "/html/verify-user.html")
);

app.listen(3000, () =>
  console.log("http://localhost:3000")
);
