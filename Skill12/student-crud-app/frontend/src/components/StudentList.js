import axios from "axios";
import { useEffect, useState } from "react";

function StudentList({ refreshTrigger }) {

  const [students, setStudents] = useState([]);
  const [editStudent, setEditStudent] = useState(null);

  useEffect(() => {
    loadStudents();
  }, [refreshTrigger]);

  const loadStudents = () => {
    axios.get("http://localhost:8080/students")
      .then(res => {
        setStudents(res.data);
      });
  };

  const deleteStudent = (id) => {
    axios.delete("http://localhost:8080/students/" + id)
      .then(() => {
        loadStudents();
      });
  };

  const handleUpdate = () => {
    axios.put("http://localhost:8080/students/" + editStudent.id, editStudent)
      .then(() => {
        setEditStudent(null);
        loadStudents();
      });
  };

  return (
    <div>
      <h2>Student List</h2>

      <table border="1">
        <thead>
          <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Course</th>
            <th>Action</th>
          </tr>
        </thead>

        <tbody>
          {students.map((s) => (
            <tr key={s.id}>
              <td>{s.id}</td>
              <td>{s.name}</td>
              <td>{s.email}</td>
              <td>{s.course}</td>
              <td>
                <button onClick={() => deleteStudent(s.id)}>Delete</button>
                <button onClick={() => setEditStudent(s)}>Edit</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>

      {editStudent && (
        <div>
          <h3>Update Student</h3>

          <input
            value={editStudent.name}
            onChange={(e) =>
              setEditStudent({ ...editStudent, name: e.target.value })
            }
          />

          <input
            value={editStudent.email}
            onChange={(e) =>
              setEditStudent({ ...editStudent, email: e.target.value })
            }
          />

          <input
            value={editStudent.course}
            onChange={(e) =>
              setEditStudent({ ...editStudent, course: e.target.value })
            }
          />

          <br /><br />

          <button onClick={handleUpdate}>Update</button>
        </div>
      )}
    </div>
  );
}

export default StudentList;