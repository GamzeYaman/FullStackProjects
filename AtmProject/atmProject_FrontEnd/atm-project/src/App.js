import './App.css';
import { Route, Routes } from 'react-router-dom'
import Register from './components/authentication/registerPage'

function App() {
  return (
    <div className="App">
      <Routes>
        <Route path='/register' element={<Register></Register>}/>
      </Routes>
    </div>
  );
}

export default App;
