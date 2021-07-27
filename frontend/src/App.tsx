import React from 'react';
import {BacklogPage} from "pages/BacklogPage";
import {Provider} from "react-redux";
import store from "store";
import ErrorModal from "components/ErrorModal";


function App() {
    return (
        <Provider store={store}>
            <BacklogPage />
            <ErrorModal />
        </Provider>
    );
}

export default App;
