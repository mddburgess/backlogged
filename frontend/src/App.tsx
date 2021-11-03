import React from 'react';
import {Provider} from "react-redux";
import store from "store";
import ErrorModal from "components/ErrorModal";
import {BrowserRouter} from 'react-router-dom';
import Routes from "routes";

function App() {
    return (
        <Provider store={store}>
            <BrowserRouter>
                <Routes />
            </BrowserRouter>
            <ErrorModal />
        </Provider>
    );
}

export default App;
