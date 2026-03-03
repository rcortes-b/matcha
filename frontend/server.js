import express from "express";
import path from "path";

const app = express();
const __dirname = path.resolve();

app.use(express.static("html"));
app.use("/css", express.static("css"));
app.use("/js", express.static("js"));
app.use("/components", express.static("components"));
app.use("/images", express.static("images"));

app.get("/loader", (_, res) =>
  res.sendFile(__dirname + "/components/loader.html")
);

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

app.get("/reset-password", (_, res) =>
  res.sendFile(__dirname + "/html/reset-password.html")
);

app.get("/complete-profile", (_, res) =>
  res.sendFile(__dirname + "/html/complete-profile.html")
);

app.listen(3000, () =>
  console.log("http://localhost:3000")
);
