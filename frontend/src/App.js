import {BrowserRouter, Route, Switch} from 'react-router-dom';
import './App.css';

function App() {
    return (
        <BrowserRouter>
            <Switch>
                <Route exact path="/">
                    <h1>Home</h1>
                </Route>
                <Route path="/hello">
                    <h1>Hello World!</h1>
                </Route>
            </Switch>
        </BrowserRouter>
    );
}

export default App;
