import {configureStore} from "@reduxjs/toolkit";
import API from "api";
import {errorMiddleware, errorSlice} from "store/error";

const store = configureStore({
    reducer: {
        [API.reducerPath]: API.reducer,
        [errorSlice.name]: errorSlice.reducer
    },
    middleware: (getDefaultMiddleware => getDefaultMiddleware().concat(API.middleware, errorMiddleware))
})

export default store;
