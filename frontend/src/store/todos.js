import {createSlice} from '@reduxjs/toolkit';
import {v4 as uuid} from 'uuid';

const todosSlice = createSlice({
    name: 'todos',
    initialState: [],
    reducers: {
        add: (state, action) => {
            state.push({
                key: uuid(),
                value: action.payload
            });
        }
    }
});

export default todosSlice.reducer;
export const {add} = todosSlice.actions;
