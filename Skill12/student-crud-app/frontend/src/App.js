import { useState } from "react";
import AddStudent from "./components/AddStudent";
import StudentList from "./components/StudentList";

function App() {

  const [refresh, setRefresh] = useState(false);

  const triggerRefresh = () => {
    setRefresh(!refresh);
  };

  return (
    <div>
      <h1>Student Management System</h1>

      <AddStudent refresh={triggerRefresh} />

      <StudentList refreshTrigger={refresh} />
    </div>
  );
}

export default App;