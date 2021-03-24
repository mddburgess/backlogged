import { Activity } from "../types/Activity";

const listActivities = (): Promise<Activity[]> => {
  return Promise.resolve([
    {
      type: "REMOVE_FROM_BACKLOG",
      date: "2021-03-23T18:39:31.000-07:00",
      title: {
        key: "42",
        name: "The Legend of Zelda: Breath of the Wild",
        copies: [],
      },
    },
    {
      type: "ADD_TO_BACKLOG",
      date: "2021-03-22T18:39:31.000-07:00",
      title: {
        key: "42",
        name: "The Legend of Zelda: Breath of the Wild",
        copies: [],
      },
    },
    {
      type: "ADD_TO_LIBRARY",
      date: "2021-03-21T18:39:31.000-07:00",
      title: {
        key: "42",
        name: "The Legend of Zelda: Breath of the Wild",
        copies: [],
      },
    },
  ]);
};

export const activities = {
  list: listActivities,
};
