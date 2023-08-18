'use client'

import {createStyles, Table, Progress, Anchor, Text, Group, ScrollArea, rem} from '@mantine/core';
import {IDish} from "@/constants/interface";

const useStyles = createStyles((theme) => ({
  progressBar: {
    '&:not(:first-of-type)': {
      borderLeft: `${rem(3)} solid ${
          theme.colorScheme === 'dark' ? theme.colors.dark[7] : theme.white
      }`,
    },
  },
}));

interface TableReviewsProps {
  data: IDish[];
}

export function TableReviews({data}: TableReviewsProps) {
  const {classes, theme} = useStyles();

  const rows = data.map((row) => {

    return (
        <tr key={row.dishId}>
          <td>
            <Anchor component="button" fz="sm">
              {row.dishId}
            </Anchor>
          </td>
          <td>{row.name}</td>
          <td>{row.proteins}</td>
          <td>{row.fats}</td>
          <td>{row.carbohydrates}</td>
          <td>{row.calories}</td>
          <td>{row.type}</td>

        </tr>
    );
  });

  return (
      <ScrollArea>
        <Table sx={{minWidth: 800}} verticalSpacing="xs">
          <thead>
          <tr>
            <th>Dish Id</th>
            <th>Name</th>
            <th>Proteins</th>
            <th>Fats</th>
            <th>Carbohydrates</th>
            <th>Calories</th>
            <th>Type</th>
          </tr>
          </thead>
          <tbody>{rows}</tbody>
        </Table>
      </ScrollArea>
  );
}