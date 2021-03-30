import { configureStore } from "@reduxjs/toolkit";
import { combineReducers } from "redux";

import activities from "./activities";
import backlog from "./backlog";
import library from "./library";

const store = configureStore({
  reducer: combineReducers({
    activities,
    backlog,
    library,
  }),
});

export type StoreState = ReturnType<typeof store.getState>;
export default store;
