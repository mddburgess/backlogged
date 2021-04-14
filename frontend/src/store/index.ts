import { configureStore } from "@reduxjs/toolkit";
import { combineReducers } from "redux";

import activities from "./activities";
import backlog from "./backlog";
import titles from "./titles";

const store = configureStore({
  reducer: combineReducers({
    activities,
    backlog,
    titles,
  }),
});

export type StoreState = ReturnType<typeof store.getState>;
export default store;
