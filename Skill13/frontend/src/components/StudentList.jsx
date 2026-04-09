import { useEffect, useState } from "react";
import axios from "axios";

function StudentList({ setEditStudent }) {
  const [students, setStudents] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  const fetchData = async () => {
    setLoading(true);
    setError("");

    try {
      const res = await axios.get(`${import.meta.env.VITE_API_URL}/students`);
      setStudents(res.data);
    } catch {
      setError("Could not load students. Please refresh and try again.");
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchData();
  }, []);

  const deleteStudent = async (id) => {
    try {
      await axios.delete(`${import.meta.env.VITE_API_URL}/students/${id}`);
      fetchData();
    } catch {
      setError("Delete failed. Please try again.");
    }
  };

  return (
    <section className="card list-card">
      <div className="card-heading list-heading">
        <h2>Student List</h2>
        <button className="btn ghost" onClick={fetchData}>
          Refresh
        </button>
      </div>

      {error && <p className="feedback error">{error}</p>}

      {loading ? (
        <p className="feedback muted">Loading students...</p>
      ) : students.length === 0 ? (
        <p className="feedback muted">No student records found yet.</p>
      ) : (
        <div className="table-wrap">
          <table>
            <thead>
              <tr>
                <th>Name</th>
                <th>Email</th>
                <th>Course</th>
                <th>Action</th>
              </tr>
            </thead>

            <tbody>
              {students.map((s) => (
                <tr key={s.id}>
                  <td>{s.name}</td>
                  <td>{s.email}</td>
                  <td>{s.course}</td>
                  <td className="actions">
                    <button className="btn small" onClick={() => setEditStudent(s)}>
                      Edit
                    </button>
                    <button className="btn small danger" onClick={() => deleteStudent(s.id)}>
                      Delete
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      )}
    </section>
  );
}

export default StudentList;
