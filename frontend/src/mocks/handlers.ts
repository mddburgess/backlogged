import { rest } from "msw";

export const handlers = [
  rest.get("/api/activities", (req, res, ctx) => {
    return res(ctx.status(200), ctx.json([]));
  }),

  rest.get("/api/backlogs", (req, res, ctx) => {
    return res(ctx.status(200), ctx.json([]));
  }),

  rest.get("/api/titles", (req, res, ctx) => {
    return res(ctx.status(200), ctx.json([]));
  }),
];
