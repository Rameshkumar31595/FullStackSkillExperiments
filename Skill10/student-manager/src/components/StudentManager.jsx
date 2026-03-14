import React, { useState } from "react";
import "./StudentManager.css";

function StudentManager() {

  const initialStudents = [
    { id: 2400031000, name: "Rahul", course: "CSE" },
    { id: 2400031001, name: "Sneha", course: "AI&DS" },
    { id: 2400031002, name: "Arjun", course: "IT" },
    { id: 2400031003, name: "Priya", course: "CS" },
    { id: 2400031004, name: "Kiran", course: "CSE" }
  ];

  const [students, setStudents] = useState(initialStudents);

  const [newStudent, setNewStudent] = useState({
    id: "",
    name: "",
    course: ""
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setNewStudent({ ...newStudent, [name]: value });
  };

  const addStudent = () => {
    if (!newStudent.id || !newStudent.name || !newStudent.course) return;

    setStudents([...students, newStudent]);

    setNewStudent({
      id: "",
      name: "",
      course: ""
    });
  };

  const deleteStudent = (id) => {
    const updated = students.filter((student) => student.id !== id);
    setStudents(updated);
  };

  return (
    <div className="container">

      <h2>Student Manager</h2>

      <div className="form">
        <input
          type="text"
          name="id"
          placeholder="Student ID"
          value={newStudent.id}
          onChange={handleChange}
        />

        <input
          type="text"
          name="name"
          placeholder="Student Name"
          value={newStudent.name}
          onChange={handleChange}
        />

        <input
          type="text"
          name="course"
          placeholder="Course"
          value={newStudent.course}
          onChange={handleChange}
        />

        <button onClick={addStudent}>Add Student</button>
      </div>

      {students.length === 0 ? (
        <p>No students available</p>
      ) : (
        <table>
          <thead>
            <tr>
              <th>ID</th>
              <th>Name</th>
              <th>Course</th>
              <th>Action</th>
            </tr>
          </thead>

          <tbody>
            {students.map((student) => (
              <tr key={student.id}>
                <td>{student.id}</td>
                <td>{student.name}</td>
                <td>{student.course}</td>
                <td>
                  <button onClick={() => deleteStudent(student.id)}>
                    Delete
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      )}

    </div>
  );
}

export default StudentManager;