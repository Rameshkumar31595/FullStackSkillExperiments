import { useState } from "react";
import StudentList from "./components/StudentList";
import AddStudent from "./components/AddStudent";

function App() {
  const [editStudent, setEditStudent] = useState(null);
  const [refresh, setRefresh] = useState(false);

  const reload = () => setRefresh(!refresh);

  return (
    <div className="app-shell">
      <header className="hero">
        <p className="hero-kicker">Student Operations Dashboard</p>
        <h1>Manage Students Smoothly</h1>
        <p className="hero-subtitle">
          Create, update, and organize records with a cleaner workflow.
        </p>
      </header>

      <main className="layout-grid">
        <AddStudent
          editStudent={editStudent}
          setEditStudent={setEditStudent}
          refresh={reload}
        />
        <StudentList setEditStudent={setEditStudent} key={refresh} />
      </main>
    </div>
  );
}

export default App;
