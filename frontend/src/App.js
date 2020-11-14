import React from 'react';
import {Provider} from 'react-redux';
import {BrowserRouter, Route, Switch} from 'react-router-dom';
import './App.css';
import Home from './routes/Home';
import store from './store';

function App() {
    return (
        <Provider store={store}>
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
        </Provider>
    );
}

export default App;
