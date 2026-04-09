import { useState, useEffect } from "react";
import axios from "axios";

function AddStudent({ editStudent, setEditStudent, refresh }) {
  const [form, setForm] = useState({
    name: "",
    email: "",
    course: ""
  });
  const [isSaving, setIsSaving] = useState(false);
  const [error, setError] = useState("");

  useEffect(() => {
    if (editStudent) {
      setForm(editStudent);
      setError("");
    }
  }, [editStudent]);

  const handleChange = (e) => {
    if (error) {
      setError("");
    }
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const clearForm = () => {
    setForm({ name: "", email: "", course: "" });
    setEditStudent(null);
    setError("");
  };

  const submit = async () => {
    if (!form.name.trim() || !form.email.trim() || !form.course.trim()) {
      setError("Please fill in all fields.");
      return;
    }

    setIsSaving(true);
    setError("");

    try {
      if (form.id) {
        await axios.put(`${import.meta.env.VITE_API_URL}/students/${form.id}`, form);
      } else {
        await axios.post(`${import.meta.env.VITE_API_URL}/students`, form);
      }

      clearForm();
      refresh();
    } catch {
      setError("Unable to save student right now. Please try again.");
    } finally {
      setIsSaving(false);
    }
  };

  return (
    <section className="card add-student-card">
      <div className="card-heading">
        <h2>{form.id ? "Update Student" : "Add Student"}</h2>
        <p>Maintain accurate student details in a few clicks.</p>
      </div>

      <div className="form-grid">
        <label>
          Name
          <input
            name="name"
            value={form.name}
            onChange={handleChange}
            placeholder="e.g. Aisha Reddy"
          />
        </label>

        <label>
          Email
          <input
            name="email"
            type="email"
            value={form.email}
            onChange={handleChange}
            placeholder="e.g. aisha@college.edu"
          />
        </label>

        <label>
          Course
          <input
            name="course"
            value={form.course}
            onChange={handleChange}
            placeholder="e.g. Full Stack Development"
          />
        </label>
      </div>

      {error && <p className="feedback error">{error}</p>}

      <div className="button-row">
        <button onClick={submit} disabled={isSaving} className="btn primary">
          {isSaving ? "Saving..." : form.id ? "Update" : "Add Student"}
        </button>
        <button onClick={clearForm} disabled={isSaving} className="btn ghost">
          Clear
        </button>
      </div>
    </section>
  );
}

export default AddStudent;
