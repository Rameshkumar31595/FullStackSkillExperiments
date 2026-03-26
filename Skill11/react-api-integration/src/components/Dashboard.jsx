import { useState } from "react";
import LocalUserList from "./LocalUserList";
import UserList from "./UserList";
import FakePostList from "./FakePostList";

const Dashboard = () => {
  const [page, setPage] = useState("home");

  return (
    <div>
      <h1>Dashboard</h1>

      <button onClick={() => setPage("local")}>Local Users</button>
      <button onClick={() => setPage("api")}>Users API</button>
      <button onClick={() => setPage("posts")}>Fake API Posts</button>

      <hr />

      {page === "local" && <LocalUserList />}
      {page === "api" && <UserList />}
      {page === "posts" && <FakePostList />}
    </div>
  );
};

export default Dashboard;