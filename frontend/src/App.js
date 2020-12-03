import React from 'react';
import {Provider} from 'react-redux';
import {BrowserRouter, Route, Switch} from 'react-router-dom';
import './App.css';
import Home from './routes/Home';
import store from './store';
import AddLibraryItem from './components/AddLibraryItem';
import EditLibraryItem from './components/EditLibraryItem';

function App() {
    return (
        <Provider store={store}>
            <BrowserRouter>
                <Switch>
                    <Route exact path="/">
                        <Home/>
                    </Route>
                    <Route path="/new">
                        <AddLibraryItem/>
                    </Route>
                    <Route path="/edit/:token">
                        <EditLibraryItem/>
                    </Route>
                </Switch>
            </BrowserRouter>
        </Provider>
    );
}

export default App;
