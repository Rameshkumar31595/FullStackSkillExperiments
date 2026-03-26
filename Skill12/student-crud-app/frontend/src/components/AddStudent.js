import axios from "axios";
import { useState } from "react";

function AddStudent({ refresh }) {

  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [course, setCourse] = useState("");

  const submit = (e) => {
    e.preventDefault();

    axios.post("http://localhost:8080/students", {
      name: name,
      email: email,
      course: course
    }).then(() => {
      setName("");
      setEmail("");
      setCourse("");
      refresh(); // update list after adding
    });
  };

  return (
    <div>
      <h2>Add Student</h2>

      <form onSubmit={submit}>
        <input
          placeholder="Name"
          value={name}
          onChange={(e) => setName(e.target.value)}
        />

        <br /><br />

        <input
          placeholder="Email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
        />

        <br /><br />

        <input
          placeholder="Course"
          value={course}
          onChange={(e) => setCourse(e.target.value)}
        />

        <br /><br />

        <button type="submit">Add Student</button>
      </form>
    </div>
  );
}

export default AddStudent;