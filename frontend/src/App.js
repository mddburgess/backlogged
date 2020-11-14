import React from 'react';
import {BrowserRouter, Route, Switch} from 'react-router-dom';
import './App.css';
import Home from './routes/Home';

function App() {
    return (
        <BrowserRouter>
            <Switch>
                <Route exact path="/">
                    <Home/>
                </Route>
                <Route path="/hello">
                    <h1>Hello World!</h1>
                </Route>
            </Switch>
        </BrowserRouter>
    );
}

export default App;
