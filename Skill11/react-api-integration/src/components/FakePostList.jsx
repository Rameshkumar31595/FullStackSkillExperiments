import { useEffect, useState } from "react";
import axios from "axios";

const FakePostList = () => {
  const [posts, setPosts] = useState([]);
  const [filteredPosts, setFilteredPosts] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [userId, setUserId] = useState("all");

  const fetchPosts = () => {
    setLoading(true);
    axios
      .get("https://dummyjson.com/posts")
      .then((res) => {
        setPosts(res.data.posts);
        setFilteredPosts(res.data.posts);
        setLoading(false);
      })
      .catch(() => {
        setError("Failed to fetch posts");
        setLoading(false);
      });
  };

  useEffect(() => {
    fetchPosts();
  }, []);

  // filter logic
  useEffect(() => {
    if (userId === "all") {
      setFilteredPosts(posts);
    } else {
      setFilteredPosts(posts.filter((p) => p.userId === Number(userId)));
    }
  }, [userId, posts]);

  if (loading) return <p>Loading posts...</p>;
  if (error) return <p>{error}</p>;

  return (
    <div>
      <h2>Fake API Posts</h2>

      <button onClick={fetchPosts}>Refresh</button>

      <br /><br />

      <label>Filter by User ID: </label>
      <select onChange={(e) => setUserId(e.target.value)}>
  <option value="all">All</option>
  {[...new Set(posts.map((p) => p.userId))].map((id) => (
    <option key={id} value={id}>
      User {id}
    </option>
  ))}
</select>

      <hr />

      {filteredPosts.map((post) => (
        <div key={post.id}>
          <h4>{post.title}</h4>
          <p>{post.body}</p>
          <small>User ID: {post.userId}</small>
          <hr />
        </div>
      ))}
    </div>
  );
};

export default FakePostList;