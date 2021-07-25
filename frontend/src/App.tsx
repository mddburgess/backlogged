import React from 'react';
import {BacklogPage} from "pages/BacklogPage";
import {Provider} from "react-redux";
import store from "store";


function App() {
    return (
        <Provider store={store}>
            <BacklogPage/>
        </Provider>
    );
}

export default App;
