import {combineReducers, configureStore} from "@reduxjs/toolkit";
import backlog from "store/backlog";

const store = configureStore({
    reducer: combineReducers({
        backlog
    })
});

export default store;
export type StoreState = ReturnType<typeof store.getState>;
