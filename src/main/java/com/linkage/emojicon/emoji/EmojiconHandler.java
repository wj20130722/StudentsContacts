package com.linkage.emojicon.emoji;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmojiconHandler
{
	private EmojiconHandler()
	{
	}

	private static final SparseIntArray sEmojisMap = new SparseIntArray(846);
	private static final SparseIntArray sSoftbanksMap = new SparseIntArray(471);

	static
	{
		// People
		sEmojisMap.put(0x1f604, 0x1f604);
		sEmojisMap.put(0x1f603, 0x1f603);
		sEmojisMap.put(0x1f600, 0x1f600);
		sEmojisMap.put(0x1f60a, 0x1f60a);
		sEmojisMap.put(0x263a, 0x263a);
		sEmojisMap.put(0x1f609, 0x1f609);
		sEmojisMap.put(0x1f60d, 0x1f60d);
		sEmojisMap.put(0x1f618, 0x1f618);
		sEmojisMap.put(0x1f61a, 0x1f61a);
		sEmojisMap.put(0x1f617, 0x1f617);
		sEmojisMap.put(0x1f619, 0x1f619);
		sEmojisMap.put(0x1f61c, 0x1f61c);
		sEmojisMap.put(0x1f61d, 0x1f61d);
		sEmojisMap.put(0x1f61b, 0x1f61b);
		sEmojisMap.put(0x1f633, 0x1f633);
		sEmojisMap.put(0x1f601, 0x1f601);
		sEmojisMap.put(0x1f614, 0x1f614);
		sEmojisMap.put(0x1f60c, 0x1f60c);
		sEmojisMap.put(0x1f612, 0x1f612);
		sEmojisMap.put(0x1f61e, 0x1f61e);
		sEmojisMap.put(0x1f623, 0x1f623);
		sEmojisMap.put(0x1f622, 0x1f622);
		sEmojisMap.put(0x1f602, 0x1f602);
		sEmojisMap.put(0x1f62d, 0x1f62d);
		sEmojisMap.put(0x1f62a, 0x1f62a);
		sEmojisMap.put(0x1f625, 0x1f625);
		sEmojisMap.put(0x1f630, 0x1f630);
		sEmojisMap.put(0x1f605, 0x1f605);
		sEmojisMap.put(0x1f613, 0x1f613);
		sEmojisMap.put(0x1f629, 0x1f629);
		sEmojisMap.put(0x1f62b, 0x1f62b);
		sEmojisMap.put(0x1f628, 0x1f628);
		sEmojisMap.put(0x1f631, 0x1f631);
		sEmojisMap.put(0x1f620, 0x1f620);
		sEmojisMap.put(0x1f621, 0x1f621);
		sEmojisMap.put(0x1f624, 0x1f624);
		sEmojisMap.put(0x1f616, 0x1f616);
		sEmojisMap.put(0x1f606, 0x1f606);
		sEmojisMap.put(0x1f60b, 0x1f60b);
		sEmojisMap.put(0x1f637, 0x1f637);
		sEmojisMap.put(0x1f60e, 0x1f60e);
		sEmojisMap.put(0x1f634, 0x1f634);
		sEmojisMap.put(0x1f635, 0x1f635);
		sEmojisMap.put(0x1f632, 0x1f632);
		sEmojisMap.put(0x1f61f, 0x1f61f);
		sEmojisMap.put(0x1f626, 0x1f626);
		sEmojisMap.put(0x1f627, 0x1f627);
		sEmojisMap.put(0x1f608, 0x1f608);
		sEmojisMap.put(0x1f47f, 0x1f47f);
		sEmojisMap.put(0x1f62e, 0x1f62e);
		sEmojisMap.put(0x1f62c, 0x1f62c);
		sEmojisMap.put(0x1f610, 0x1f610);
		sEmojisMap.put(0x1f615, 0x1f615);
		sEmojisMap.put(0x1f62f, 0x1f62f);
		sEmojisMap.put(0x1f636, 0x1f636);
		sEmojisMap.put(0x1f607, 0x1f607);
		sEmojisMap.put(0x1f60f, 0x1f60f);
		sEmojisMap.put(0x1f611, 0x1f611);
		sEmojisMap.put(0x1f472, 0x1f472);
		sEmojisMap.put(0x1f473, 0x1f473);
		sEmojisMap.put(0x1f46e, 0x1f46e);
		sEmojisMap.put(0x1f477, 0x1f477);
		sEmojisMap.put(0x1f482, 0x1f482);
		sEmojisMap.put(0x1f476, 0x1f476);
		sEmojisMap.put(0x1f466, 0x1f466);
		sEmojisMap.put(0x1f467, 0x1f467);
		sEmojisMap.put(0x1f468, 0x1f468);
		sEmojisMap.put(0x1f469, 0x1f469);
		sEmojisMap.put(0x1f474, 0x1f474);
		sEmojisMap.put(0x1f475, 0x1f475);
		sEmojisMap.put(0x1f471, 0x1f471);
		sEmojisMap.put(0x1f47c, 0x1f47c);
		sEmojisMap.put(0x1f478, 0x1f478);
		sEmojisMap.put(0x1f63a, 0x1f63a);
		sEmojisMap.put(0x1f638, 0x1f638);
		sEmojisMap.put(0x1f63b, 0x1f63b);
		sEmojisMap.put(0x1f63d, 0x1f63d);
		sEmojisMap.put(0x1f63c, 0x1f63c);
		sEmojisMap.put(0x1f640, 0x1f640);
		sEmojisMap.put(0x1f63f, 0x1f63f);
		sEmojisMap.put(0x1f639, 0x1f639);
		sEmojisMap.put(0x1f63e, 0x1f63e);
		sEmojisMap.put(0x1f479, 0x1f479);
		sEmojisMap.put(0x1f47a, 0x1f47a);
		sEmojisMap.put(0x1f648, 0x1f648);
		sEmojisMap.put(0x1f649, 0x1f649);
		sEmojisMap.put(0x1f64a, 0x1f64a);
		sEmojisMap.put(0x1f480, 0x1f480);
		sEmojisMap.put(0x1f47d, 0x1f47d);
		sEmojisMap.put(0x1f4a9, 0x1f4a9);
		sEmojisMap.put(0x1f525, 0x1f525);
		sEmojisMap.put(0x2728, 0x2728);
		sEmojisMap.put(0x1f31f, 0x1f31f);
		sEmojisMap.put(0x1f4ab, 0x1f4ab);
		sEmojisMap.put(0x1f4a5, 0x1f4a5);
		sEmojisMap.put(0x1f4a2, 0x1f4a2);
		sEmojisMap.put(0x1f4a6, 0x1f4a6);
		sEmojisMap.put(0x1f4a7, 0x1f4a7);
		sEmojisMap.put(0x1f4a4, 0x1f4a4);
		sEmojisMap.put(0x1f4a8, 0x1f4a8);
		sEmojisMap.put(0x1f442, 0x1f442);
		sEmojisMap.put(0x1f440, 0x1f440);
		sEmojisMap.put(0x1f443, 0x1f443);
		sEmojisMap.put(0x1f445, 0x1f445);
		sEmojisMap.put(0x1f444, 0x1f444);
		sEmojisMap.put(0x1f44d, 0x1f44d);
		sEmojisMap.put(0x1f44e, 0x1f44e);
		sEmojisMap.put(0x1f44c, 0x1f44c);
		sEmojisMap.put(0x1f44a, 0x1f44a);
		sEmojisMap.put(0x270a, 0x270a);
		sEmojisMap.put(0x270c, 0x270c);
		sEmojisMap.put(0x1f44b, 0x1f44b);
		sEmojisMap.put(0x270b, 0x270b);
		sEmojisMap.put(0x1f450, 0x1f450);
		sEmojisMap.put(0x1f446, 0x1f446);
		sEmojisMap.put(0x1f447, 0x1f447);
		sEmojisMap.put(0x1f449, 0x1f449);
		sEmojisMap.put(0x1f448, 0x1f448);
		sEmojisMap.put(0x1f64c, 0x1f64c);
		sEmojisMap.put(0x1f64f, 0x1f64f);
		sEmojisMap.put(0x261d, 0x261d);
		sEmojisMap.put(0x1f44f, 0x1f44f);
		sEmojisMap.put(0x1f4aa, 0x1f4aa);
		sEmojisMap.put(0x1f6b6, 0x1f6b6);
		sEmojisMap.put(0x1f3c3, 0x1f3c3);
		sEmojisMap.put(0x1f483, 0x1f483);
		sEmojisMap.put(0x1f46b, 0x1f46b);
		sEmojisMap.put(0x1f46a, 0x1f46a);
		sEmojisMap.put(0x1f46c, 0x1f46c);
		sEmojisMap.put(0x1f46d, 0x1f46d);
		sEmojisMap.put(0x1f48f, 0x1f48f);
		sEmojisMap.put(0x1f491, 0x1f491);
		sEmojisMap.put(0x1f46f, 0x1f46f);
		sEmojisMap.put(0x1f646, 0x1f646);
		sEmojisMap.put(0x1f645, 0x1f645);
		sEmojisMap.put(0x1f481, 0x1f481);
		sEmojisMap.put(0x1f64b, 0x1f64b);
		sEmojisMap.put(0x1f486, 0x1f486);
		sEmojisMap.put(0x1f487, 0x1f487);
		sEmojisMap.put(0x1f485, 0x1f485);
		sEmojisMap.put(0x1f470, 0x1f470);
		sEmojisMap.put(0x1f64e, 0x1f64e);
		sEmojisMap.put(0x1f64d, 0x1f64d);
		sEmojisMap.put(0x1f647, 0x1f647);
		sEmojisMap.put(0x1f3a9, 0x1f3a9);
		sEmojisMap.put(0x1f451, 0x1f451);
		sEmojisMap.put(0x1f452, 0x1f452);
		sEmojisMap.put(0x1f45f, 0x1f45f);
		sEmojisMap.put(0x1f45e, 0x1f45e);
		sEmojisMap.put(0x1f461, 0x1f461);
		sEmojisMap.put(0x1f460, 0x1f460);
		sEmojisMap.put(0x1f462, 0x1f462);
		sEmojisMap.put(0x1f455, 0x1f455);
		sEmojisMap.put(0x1f454, 0x1f454);
		sEmojisMap.put(0x1f45a, 0x1f45a);
		sEmojisMap.put(0x1f457, 0x1f457);
		sEmojisMap.put(0x1f3bd, 0x1f3bd);
		sEmojisMap.put(0x1f456, 0x1f456);
		sEmojisMap.put(0x1f458, 0x1f458);
		sEmojisMap.put(0x1f459, 0x1f459);
		sEmojisMap.put(0x1f4bc, 0x1f4bc);
		sEmojisMap.put(0x1f45c, 0x1f45c);
		sEmojisMap.put(0x1f45d, 0x1f45d);
		sEmojisMap.put(0x1f45b, 0x1f45b);
		sEmojisMap.put(0x1f453, 0x1f453);
		sEmojisMap.put(0x1f380, 0x1f380);
		sEmojisMap.put(0x1f302, 0x1f302);
		sEmojisMap.put(0x1f484, 0x1f484);
		sEmojisMap.put(0x1f49b, 0x1f49b);
		sEmojisMap.put(0x1f499, 0x1f499);
		sEmojisMap.put(0x1f49c, 0x1f49c);
		sEmojisMap.put(0x1f49a, 0x1f49a);
		sEmojisMap.put(0x2764, 0x2764);
		sEmojisMap.put(0x1f494, 0x1f494);
		sEmojisMap.put(0x1f497, 0x1f497);
		sEmojisMap.put(0x1f493, 0x1f493);
		sEmojisMap.put(0x1f495, 0x1f495);
		sEmojisMap.put(0x1f496, 0x1f496);
		sEmojisMap.put(0x1f49e, 0x1f49e);
		sEmojisMap.put(0x1f498, 0x1f498);
		sEmojisMap.put(0x1f48c, 0x1f48c);
		sEmojisMap.put(0x1f48b, 0x1f48b);
		sEmojisMap.put(0x1f48d, 0x1f48d);
		sEmojisMap.put(0x1f48e, 0x1f48e);
		sEmojisMap.put(0x1f464, 0x1f464);
		sEmojisMap.put(0x1f465, 0x1f465);
		sEmojisMap.put(0x1f4ac, 0x1f4ac);
		sEmojisMap.put(0x1f463, 0x1f463);
		sEmojisMap.put(0x1f4ad, 0x1f4ad);

		// Nature
		sEmojisMap.put(0x1f436, 0x1f436);
		sEmojisMap.put(0x1f43a, 0x1f43a);
		sEmojisMap.put(0x1f431, 0x1f431);
		sEmojisMap.put(0x1f42d, 0x1f42d);
		sEmojisMap.put(0x1f439, 0x1f439);
		sEmojisMap.put(0x1f430, 0x1f430);
		sEmojisMap.put(0x1f438, 0x1f438);
		sEmojisMap.put(0x1f42f, 0x1f42f);
		sEmojisMap.put(0x1f428, 0x1f428);
		sEmojisMap.put(0x1f43b, 0x1f43b);
		sEmojisMap.put(0x1f437, 0x1f437);
		sEmojisMap.put(0x1f43d, 0x1f43d);
		sEmojisMap.put(0x1f42e, 0x1f42e);
		sEmojisMap.put(0x1f417, 0x1f417);
		sEmojisMap.put(0x1f435, 0x1f435);
		sEmojisMap.put(0x1f412, 0x1f412);
		sEmojisMap.put(0x1f434, 0x1f434);
		sEmojisMap.put(0x1f411, 0x1f411);
		sEmojisMap.put(0x1f418, 0x1f418);
		sEmojisMap.put(0x1f43c, 0x1f43c);
		sEmojisMap.put(0x1f427, 0x1f427);
		sEmojisMap.put(0x1f426, 0x1f426);
		sEmojisMap.put(0x1f424, 0x1f424);
		sEmojisMap.put(0x1f425, 0x1f425);
		sEmojisMap.put(0x1f423, 0x1f423);
		sEmojisMap.put(0x1f414, 0x1f414);
		sEmojisMap.put(0x1f40d, 0x1f40d);
		sEmojisMap.put(0x1f422, 0x1f422);
		sEmojisMap.put(0x1f41b, 0x1f41b);
		sEmojisMap.put(0x1f41d, 0x1f41d);
		sEmojisMap.put(0x1f41c, 0x1f41c);
		sEmojisMap.put(0x1f41e, 0x1f41e);
		sEmojisMap.put(0x1f40c, 0x1f40c);
		sEmojisMap.put(0x1f419, 0x1f419);
		sEmojisMap.put(0x1f41a, 0x1f41a);
		sEmojisMap.put(0x1f420, 0x1f420);
		sEmojisMap.put(0x1f41f, 0x1f41f);
		sEmojisMap.put(0x1f42c, 0x1f42c);
		sEmojisMap.put(0x1f433, 0x1f433);
		sEmojisMap.put(0x1f40b, 0x1f40b);
		sEmojisMap.put(0x1f404, 0x1f404);
		sEmojisMap.put(0x1f40f, 0x1f40f);
		sEmojisMap.put(0x1f400, 0x1f400);
		sEmojisMap.put(0x1f403, 0x1f403);
		sEmojisMap.put(0x1f405, 0x1f405);
		sEmojisMap.put(0x1f407, 0x1f407);
		sEmojisMap.put(0x1f409, 0x1f409);
		sEmojisMap.put(0x1f40e, 0x1f40e);
		sEmojisMap.put(0x1f410, 0x1f410);
		sEmojisMap.put(0x1f413, 0x1f413);
		sEmojisMap.put(0x1f415, 0x1f415);
		sEmojisMap.put(0x1f416, 0x1f416);
		sEmojisMap.put(0x1f401, 0x1f401);
		sEmojisMap.put(0x1f402, 0x1f402);
		sEmojisMap.put(0x1f432, 0x1f432);
		sEmojisMap.put(0x1f421, 0x1f421);
		sEmojisMap.put(0x1f40a, 0x1f40a);
		sEmojisMap.put(0x1f42b, 0x1f42b);
		sEmojisMap.put(0x1f42a, 0x1f42a);
		sEmojisMap.put(0x1f406, 0x1f406);
		sEmojisMap.put(0x1f408, 0x1f408);
		sEmojisMap.put(0x1f429, 0x1f429);
		sEmojisMap.put(0x1f43e, 0x1f43e);
		sEmojisMap.put(0x1f490, 0x1f490);
		sEmojisMap.put(0x1f338, 0x1f338);
		sEmojisMap.put(0x1f337, 0x1f337);
		sEmojisMap.put(0x1f340, 0x1f340);
		sEmojisMap.put(0x1f339, 0x1f339);
		sEmojisMap.put(0x1f33b, 0x1f33b);
		sEmojisMap.put(0x1f33a, 0x1f33a);
		sEmojisMap.put(0x1f341, 0x1f341);
		sEmojisMap.put(0x1f343, 0x1f343);
		sEmojisMap.put(0x1f342, 0x1f342);
		sEmojisMap.put(0x1f33f, 0x1f33f);
		sEmojisMap.put(0x1f33e, 0x1f33e);
		sEmojisMap.put(0x1f344, 0x1f344);
		sEmojisMap.put(0x1f335, 0x1f335);
		sEmojisMap.put(0x1f334, 0x1f334);
		sEmojisMap.put(0x1f332, 0x1f332);
		sEmojisMap.put(0x1f333, 0x1f333);
		sEmojisMap.put(0x1f330, 0x1f330);
		sEmojisMap.put(0x1f331, 0x1f331);
		sEmojisMap.put(0x1f33c, 0x1f33c);
		sEmojisMap.put(0x1f310, 0x1f310);
		sEmojisMap.put(0x1f31e, 0x1f31e);
		sEmojisMap.put(0x1f31d, 0x1f31d);
		sEmojisMap.put(0x1f31a, 0x1f31a);
		sEmojisMap.put(0x1f311, 0x1f311);
		sEmojisMap.put(0x1f312, 0x1f312);
		sEmojisMap.put(0x1f313, 0x1f313);
		sEmojisMap.put(0x1f314, 0x1f314);
		sEmojisMap.put(0x1f315, 0x1f315);
		sEmojisMap.put(0x1f316, 0x1f316);
		sEmojisMap.put(0x1f317, 0x1f317);
		sEmojisMap.put(0x1f318, 0x1f318);
		sEmojisMap.put(0x1f31c, 0x1f31c);
		sEmojisMap.put(0x1f31b, 0x1f31b);
		sEmojisMap.put(0x1f319, 0x1f319);
		sEmojisMap.put(0x1f30d, 0x1f30d);
		sEmojisMap.put(0x1f30e, 0x1f30e);
		sEmojisMap.put(0x1f30f, 0x1f30f);
		sEmojisMap.put(0x1f30b, 0x1f30b);
		sEmojisMap.put(0x1f30c, 0x1f30c);
		sEmojisMap.put(0x1f320, 0x1f303); // TODO (rockerhieu) review this emoji
		sEmojisMap.put(0x2b50, 0x2b50);
		sEmojisMap.put(0x2600, 0x2600);
		sEmojisMap.put(0x26c5, 0x26c5);
		sEmojisMap.put(0x2601, 0x2601);
		sEmojisMap.put(0x26a1, 0x26a1);
		sEmojisMap.put(0x2614, 0x2614);
		sEmojisMap.put(0x2744, 0x2744);
		sEmojisMap.put(0x26c4, 0x26c4);
		sEmojisMap.put(0x1f300, 0x1f300);
		sEmojisMap.put(0x1f301, 0x1f301);
		sEmojisMap.put(0x1f308, 0x1f308);
		sEmojisMap.put(0x1f30a, 0x1f30a);

		// Objects
		sEmojisMap.put(0x1f38d, 0x1f38d);
		sEmojisMap.put(0x1f49d, 0x1f49d);
		sEmojisMap.put(0x1f38e, 0x1f38e);
		sEmojisMap.put(0x1f392, 0x1f392);
		sEmojisMap.put(0x1f393, 0x1f393);
		sEmojisMap.put(0x1f38f, 0x1f38f);
		sEmojisMap.put(0x1f386, 0x1f386);
		sEmojisMap.put(0x1f387, 0x1f387);
		sEmojisMap.put(0x1f390, 0x1f390);
		sEmojisMap.put(0x1f391, 0x1f391);
		sEmojisMap.put(0x1f383, 0x1f383);
		sEmojisMap.put(0x1f47b, 0x1f47b);
		sEmojisMap.put(0x1f385, 0x1f385);
		sEmojisMap.put(0x1f384, 0x1f384);
		sEmojisMap.put(0x1f381, 0x1f381);
		sEmojisMap.put(0x1f38b, 0x1f38b);
		sEmojisMap.put(0x1f389, 0x1f389);
		sEmojisMap.put(0x1f38a, 0x1f38a);
		sEmojisMap.put(0x1f388, 0x1f388);
		sEmojisMap.put(0x1f38c, 0x1f38c);
		sEmojisMap.put(0x1f52e, 0x1f52e);
		sEmojisMap.put(0x1f3a5, 0x1f3a5);
		sEmojisMap.put(0x1f4f7, 0x1f4f7);
		sEmojisMap.put(0x1f4f9, 0x1f4f9);
		sEmojisMap.put(0x1f4fc, 0x1f4fc);
		sEmojisMap.put(0x1f4bf, 0x1f4bf);
		sEmojisMap.put(0x1f4c0, 0x1f4c0);
		sEmojisMap.put(0x1f4bd, 0x1f4bd);
		sEmojisMap.put(0x1f4be, 0x1f4be);
		sEmojisMap.put(0x1f4bb, 0x1f4bb);
		sEmojisMap.put(0x1f4f1, 0x1f4f1);
		sEmojisMap.put(0x260e, 0x260e);
		sEmojisMap.put(0x1f4de, 0x1f4de);
		sEmojisMap.put(0x1f4df, 0x1f4df);
		sEmojisMap.put(0x1f4e0, 0x1f4e0);
		sEmojisMap.put(0x1f4e1, 0x1f4e1);
		sEmojisMap.put(0x1f4fa, 0x1f4fa);
		sEmojisMap.put(0x1f4fb, 0x1f4fb);
		sEmojisMap.put(0x1f50a, 0x1f50a);
		sEmojisMap.put(0x1f509, 0x1f509);
		sEmojisMap.put(0x1f508, 0x1f508); // TODO (rockerhieu): review this emoji
		sEmojisMap.put(0x1f507, 0x1f507);
		sEmojisMap.put(0x1f514, 0x1f514);
		sEmojisMap.put(0x1f515, 0x1f515);
		sEmojisMap.put(0x1f4e2, 0x1f4e2);
		sEmojisMap.put(0x1f4e3, 0x1f4e3);
		sEmojisMap.put(0x23f3, 0x23f3);
		sEmojisMap.put(0x231b, 0x231b);
		sEmojisMap.put(0x23f0, 0x23f0);
		sEmojisMap.put(0x231a, 0x231a);
		sEmojisMap.put(0x1f513, 0x1f513);
		sEmojisMap.put(0x1f512, 0x1f512);
		sEmojisMap.put(0x1f50f, 0x1f50f);
		sEmojisMap.put(0x1f510, 0x1f510);
		sEmojisMap.put(0x1f511, 0x1f511);
		sEmojisMap.put(0x1f50e, 0x1f50e);
		sEmojisMap.put(0x1f4a1, 0x1f4a1);
		sEmojisMap.put(0x1f526, 0x1f526);
		sEmojisMap.put(0x1f506, 0x1f506);
		sEmojisMap.put(0x1f505, 0x1f505);
		sEmojisMap.put(0x1f50c, 0x1f50c);
		sEmojisMap.put(0x1f50b, 0x1f50b);
		sEmojisMap.put(0x1f50d, 0x1f50d);
		sEmojisMap.put(0x1f6c1, 0x1f6c1);
		sEmojisMap.put(0x1f6c0, 0x1f6c0);
		sEmojisMap.put(0x1f6bf, 0x1f6bf);
		sEmojisMap.put(0x1f6bd, 0x1f6bd);
		sEmojisMap.put(0x1f527, 0x1f527);
		sEmojisMap.put(0x1f529, 0x1f529);
		sEmojisMap.put(0x1f528, 0x1f528);
		sEmojisMap.put(0x1f6aa, 0x1f6aa);
		sEmojisMap.put(0x1f6ac, 0x1f6ac);
		sEmojisMap.put(0x1f4a3, 0x1f4a3);
		sEmojisMap.put(0x1f52b, 0x1f52b);
		sEmojisMap.put(0x1f52a, 0x1f52a);
		sEmojisMap.put(0x1f48a, 0x1f48a);
		sEmojisMap.put(0x1f489, 0x1f489);
		sEmojisMap.put(0x1f4b0, 0x1f4b0);
		sEmojisMap.put(0x1f4b4, 0x1f4b4);
		sEmojisMap.put(0x1f4b5, 0x1f4b5);
		sEmojisMap.put(0x1f4b7, 0x1f4b7);
		sEmojisMap.put(0x1f4b6, 0x1f4b6);
		sEmojisMap.put(0x1f4b3, 0x1f4b3);
		sEmojisMap.put(0x1f4b8, 0x1f4b8);
		sEmojisMap.put(0x1f4f2, 0x1f4f2);
		sEmojisMap.put(0x1f4e7, 0x1f4e7);
		sEmojisMap.put(0x1f4e5, 0x1f4e5);
		sEmojisMap.put(0x1f4e4, 0x1f4e4);
		sEmojisMap.put(0x2709, 0x2709);
		sEmojisMap.put(0x1f4e9, 0x1f4e9);
		sEmojisMap.put(0x1f4e8, 0x1f4e8);
		sEmojisMap.put(0x1f4ef, 0x1f4ef);
		sEmojisMap.put(0x1f4eb, 0x1f4eb);
		sEmojisMap.put(0x1f4ea, 0x1f4ea);
		sEmojisMap.put(0x1f4ec, 0x1f4ec);
		sEmojisMap.put(0x1f4ed, 0x1f4ed);
		sEmojisMap.put(0x1f4ee, 0x1f4ee);
		sEmojisMap.put(0x1f4e6, 0x1f4e6);
		sEmojisMap.put(0x1f4dd, 0x1f4dd);
		sEmojisMap.put(0x1f4c4, 0x1f4c4);
		sEmojisMap.put(0x1f4c3, 0x1f4c3);
		sEmojisMap.put(0x1f4d1, 0x1f4d1);
		sEmojisMap.put(0x1f4ca, 0x1f4ca);
		sEmojisMap.put(0x1f4c8, 0x1f4c8);
		sEmojisMap.put(0x1f4c9, 0x1f4c9);
		sEmojisMap.put(0x1f4dc, 0x1f4dc);
		sEmojisMap.put(0x1f4cb, 0x1f4cb);
		sEmojisMap.put(0x1f4c5, 0x1f4c5);
		sEmojisMap.put(0x1f4c6, 0x1f4c6);
		sEmojisMap.put(0x1f4c7, 0x1f4c7);
		sEmojisMap.put(0x1f4c1, 0x1f4c1);
		sEmojisMap.put(0x1f4c2, 0x1f4c2);
		sEmojisMap.put(0x2702, 0x2702);
		sEmojisMap.put(0x1f4cc, 0x1f4cc);
		sEmojisMap.put(0x1f4ce, 0x1f4ce);
		sEmojisMap.put(0x2712, 0x2712);
		sEmojisMap.put(0x270f, 0x270f);
		sEmojisMap.put(0x1f4cf, 0x1f4cf);
		sEmojisMap.put(0x1f4d0, 0x1f4d0);
		sEmojisMap.put(0x1f4d5, 0x1f4d5);
		sEmojisMap.put(0x1f4d7, 0x1f4d7);
		sEmojisMap.put(0x1f4d8, 0x1f4d8);
		sEmojisMap.put(0x1f4d9, 0x1f4d9);
		sEmojisMap.put(0x1f4d3, 0x1f4d3);
		sEmojisMap.put(0x1f4d4, 0x1f4d4);
		sEmojisMap.put(0x1f4d2, 0x1f4d2);
		sEmojisMap.put(0x1f4da, 0x1f4da);
		sEmojisMap.put(0x1f4d6, 0x1f4d6);
		sEmojisMap.put(0x1f516, 0x1f516);
		sEmojisMap.put(0x1f4db, 0x1f4db);
		sEmojisMap.put(0x1f52c, 0x1f52c);
		sEmojisMap.put(0x1f52d, 0x1f52d);
		sEmojisMap.put(0x1f4f0, 0x1f4f0);
		sEmojisMap.put(0x1f3a8, 0x1f3a8);
		sEmojisMap.put(0x1f3ac, 0x1f3ac);
		sEmojisMap.put(0x1f3a4, 0x1f3a4);
		sEmojisMap.put(0x1f3a7, 0x1f3a7);
		sEmojisMap.put(0x1f3bc, 0x1f3bc);
		sEmojisMap.put(0x1f3b5, 0x1f3b5);
		sEmojisMap.put(0x1f3b6, 0x1f3b6);
		sEmojisMap.put(0x1f3b9, 0x1f3b9);
		sEmojisMap.put(0x1f3bb, 0x1f3bb);
		sEmojisMap.put(0x1f3ba, 0x1f3ba);
		sEmojisMap.put(0x1f3b7, 0x1f3b7);
		sEmojisMap.put(0x1f3b8, 0x1f3b8);
		sEmojisMap.put(0x1f47e, 0x1f47e);
		sEmojisMap.put(0x1f3ae, 0x1f3ae);
		sEmojisMap.put(0x1f0cf, 0x1f0cf);
		sEmojisMap.put(0x1f3b4, 0x1f3b4);
		sEmojisMap.put(0x1f004, 0x1f004);
		sEmojisMap.put(0x1f3b2, 0x1f3b2);
		sEmojisMap.put(0x1f3af, 0x1f3af);
		sEmojisMap.put(0x1f3c8, 0x1f3c8);
		sEmojisMap.put(0x1f3c0, 0x1f3c0);
		sEmojisMap.put(0x26bd, 0x26bd);
		sEmojisMap.put(0x26be, 0x26be);
		sEmojisMap.put(0x1f3be, 0x1f3be);
		sEmojisMap.put(0x1f3b1, 0x1f3b1);
		sEmojisMap.put(0x1f3c9, 0x1f3c9);
		sEmojisMap.put(0x1f3b3, 0x1f3b3);
		sEmojisMap.put(0x26f3, 0x26f3);
		sEmojisMap.put(0x1f6b5, 0x1f6b5);
		sEmojisMap.put(0x1f6b4, 0x1f6b4);
		sEmojisMap.put(0x1f3c1, 0x1f3c1);
		sEmojisMap.put(0x1f3c7, 0x1f3c7);
		sEmojisMap.put(0x1f3c6, 0x1f3c6);
		sEmojisMap.put(0x1f3bf, 0x1f3bf);
		sEmojisMap.put(0x1f3c2, 0x1f3c2);
		sEmojisMap.put(0x1f3ca, 0x1f3ca);
		sEmojisMap.put(0x1f3c4, 0x1f3c4);
		sEmojisMap.put(0x1f3a3, 0x1f3a3);
		sEmojisMap.put(0x2615, 0x2615);
		sEmojisMap.put(0x1f375, 0x1f375);
		sEmojisMap.put(0x1f376, 0x1f376);
		sEmojisMap.put(0x1f37c, 0x1f37c);
		sEmojisMap.put(0x1f37a, 0x1f37a);
		sEmojisMap.put(0x1f37b, 0x1f37b);
		sEmojisMap.put(0x1f378, 0x1f378);
		sEmojisMap.put(0x1f379, 0x1f379);
		sEmojisMap.put(0x1f377, 0x1f377);
		sEmojisMap.put(0x1f374, 0x1f374);
		sEmojisMap.put(0x1f355, 0x1f355);
		sEmojisMap.put(0x1f354, 0x1f354);
		sEmojisMap.put(0x1f35f, 0x1f35f);
		sEmojisMap.put(0x1f357, 0x1f357);
		sEmojisMap.put(0x1f356, 0x1f356);
		sEmojisMap.put(0x1f35d, 0x1f35d);
		sEmojisMap.put(0x1f35b, 0x1f35b);
		sEmojisMap.put(0x1f364, 0x1f364);
		sEmojisMap.put(0x1f371, 0x1f371);
		sEmojisMap.put(0x1f363, 0x1f363);
		sEmojisMap.put(0x1f365, 0x1f365);
		sEmojisMap.put(0x1f359, 0x1f359);
		sEmojisMap.put(0x1f358, 0x1f358);
		sEmojisMap.put(0x1f35a, 0x1f35a);
		sEmojisMap.put(0x1f35c, 0x1f35c);
		sEmojisMap.put(0x1f372, 0x1f372);
		sEmojisMap.put(0x1f362, 0x1f362);
		sEmojisMap.put(0x1f361, 0x1f361);
		sEmojisMap.put(0x1f373, 0x1f373);
		sEmojisMap.put(0x1f35e, 0x1f35e);
		sEmojisMap.put(0x1f369, 0x1f369);
		sEmojisMap.put(0x1f36e, 0x1f36e);
		sEmojisMap.put(0x1f366, 0x1f366);
		sEmojisMap.put(0x1f368, 0x1f368);
		sEmojisMap.put(0x1f367, 0x1f367);
		sEmojisMap.put(0x1f382, 0x1f382);
		sEmojisMap.put(0x1f370, 0x1f370);
		sEmojisMap.put(0x1f36a, 0x1f36a);
		sEmojisMap.put(0x1f36b, 0x1f36b);
		sEmojisMap.put(0x1f36c, 0x1f36c);
		sEmojisMap.put(0x1f36d, 0x1f36d);
		sEmojisMap.put(0x1f36f, 0x1f36f);
		sEmojisMap.put(0x1f34e, 0x1f34e);
		sEmojisMap.put(0x1f34f, 0x1f34f);
		sEmojisMap.put(0x1f34a, 0x1f34a);
		sEmojisMap.put(0x1f34b, 0x1f34b);
		sEmojisMap.put(0x1f352, 0x1f352);
		sEmojisMap.put(0x1f347, 0x1f347);
		sEmojisMap.put(0x1f349, 0x1f349);
		sEmojisMap.put(0x1f353, 0x1f353);
		sEmojisMap.put(0x1f351, 0x1f351);
		sEmojisMap.put(0x1f348, 0x1f348);
		sEmojisMap.put(0x1f34c, 0x1f34c);
		sEmojisMap.put(0x1f350, 0x1f350);
		sEmojisMap.put(0x1f34d, 0x1f34d);
		sEmojisMap.put(0x1f360, 0x1f360);
		sEmojisMap.put(0x1f346, 0x1f346);
		sEmojisMap.put(0x1f345, 0x1f345);
		sEmojisMap.put(0x1f33d, 0x1f33d);

		// Places
		sEmojisMap.put(0x1f3e0, 0x1f3e0);
		sEmojisMap.put(0x1f3e1, 0x1f3e1);
		sEmojisMap.put(0x1f3eb, 0x1f3eb);
		sEmojisMap.put(0x1f3e2, 0x1f3e2);
		sEmojisMap.put(0x1f3e3, 0x1f3e3);
		sEmojisMap.put(0x1f3e5, 0x1f3e5);
		sEmojisMap.put(0x1f3e6, 0x1f3e6);
		sEmojisMap.put(0x1f3ea, 0x1f3ea);
		sEmojisMap.put(0x1f3e9, 0x1f3e9);
		sEmojisMap.put(0x1f3e8, 0x1f3e8);
		sEmojisMap.put(0x1f492, 0x1f492);
		sEmojisMap.put(0x26ea, 0x26ea);
		sEmojisMap.put(0x1f3ec, 0x1f3ec);
		sEmojisMap.put(0x1f3e4, 0x1f3e4);
		sEmojisMap.put(0x1f307, 0x1f307);
		sEmojisMap.put(0x1f306, 0x1f306);
		sEmojisMap.put(0x1f3ef, 0x1f3ef);
		sEmojisMap.put(0x1f3f0, 0x1f3f0);
		sEmojisMap.put(0x26fa, 0x26fa);
		sEmojisMap.put(0x1f3ed, 0x1f3ed);
		sEmojisMap.put(0x1f5fc, 0x1f5fc);
		sEmojisMap.put(0x1f5fe, 0x1f5fe);
		sEmojisMap.put(0x1f5fb, 0x1f5fb);
		sEmojisMap.put(0x1f304, 0x1f304);
		sEmojisMap.put(0x1f305, 0x1f305);
		sEmojisMap.put(0x1f303, 0x1f303);
		sEmojisMap.put(0x1f5fd, 0x1f5fd);
		sEmojisMap.put(0x1f309, 0x1f309);
		sEmojisMap.put(0x1f3a0, 0x1f3a0);
		sEmojisMap.put(0x1f3a1, 0x1f3a1);
		sEmojisMap.put(0x26f2, 0x26f2);
		sEmojisMap.put(0x1f3a2, 0x1f3a2);
		sEmojisMap.put(0x1f6a2, 0x1f6a2);
		sEmojisMap.put(0x26f5, 0x26f5);
		sEmojisMap.put(0x1f6a4, 0x1f6a4);
		sEmojisMap.put(0x1f6a3, 0x1f6a3);
		sEmojisMap.put(0x2693, 0x2693);
		sEmojisMap.put(0x1f680, 0x1f680);
		sEmojisMap.put(0x2708, 0x2708);
		sEmojisMap.put(0x1f4ba, 0x1f4ba);
		sEmojisMap.put(0x1f681, 0x1f681);
		sEmojisMap.put(0x1f682, 0x1f682);
		sEmojisMap.put(0x1f68a, 0x1f68a);
		sEmojisMap.put(0x1f689, 0x1f689);
		sEmojisMap.put(0x1f69e, 0x1f69e);
		sEmojisMap.put(0x1f686, 0x1f686);
		sEmojisMap.put(0x1f684, 0x1f684);
		sEmojisMap.put(0x1f685, 0x1f685);
		sEmojisMap.put(0x1f688, 0x1f688);
		sEmojisMap.put(0x1f687, 0x1f687);
		sEmojisMap.put(0x1f69d, 0x1f69d);
		sEmojisMap.put(0x1f68b, 0x1f68b); // TODO (rockerhieu) review this emoji
		sEmojisMap.put(0x1f683, 0x1f683);
		sEmojisMap.put(0x1f68e, 0x1f68e);
		sEmojisMap.put(0x1f68c, 0x1f68c);
		sEmojisMap.put(0x1f68d, 0x1f68d);
		sEmojisMap.put(0x1f699, 0x1f699);
		sEmojisMap.put(0x1f698, 0x1f698);
		sEmojisMap.put(0x1f697, 0x1f697);
		sEmojisMap.put(0x1f695, 0x1f695);
		sEmojisMap.put(0x1f696, 0x1f696);
		sEmojisMap.put(0x1f69b, 0x1f69b);
		sEmojisMap.put(0x1f69a, 0x1f69a);
		sEmojisMap.put(0x1f6a8, 0x1f6a8);
		sEmojisMap.put(0x1f693, 0x1f693);
		sEmojisMap.put(0x1f694, 0x1f694);
		sEmojisMap.put(0x1f692, 0x1f692);
		sEmojisMap.put(0x1f691, 0x1f691);
		sEmojisMap.put(0x1f690, 0x1f690);
		sEmojisMap.put(0x1f6b2, 0x1f6b2);
		sEmojisMap.put(0x1f6a1, 0x1f6a1);
		sEmojisMap.put(0x1f69f, 0x1f69f);
		sEmojisMap.put(0x1f6a0, 0x1f6a0);
		sEmojisMap.put(0x1f69c, 0x1f69c);
		sEmojisMap.put(0x1f488, 0x1f488);
		sEmojisMap.put(0x1f68f, 0x1f68f);
		sEmojisMap.put(0x1f3ab, 0x1f3ab);
		sEmojisMap.put(0x1f6a6, 0x1f6a6);
		sEmojisMap.put(0x1f6a5, 0x1f6a5);
		sEmojisMap.put(0x26a0, 0x26a0);
		sEmojisMap.put(0x1f6a7, 0x1f6a7);
		sEmojisMap.put(0x1f530, 0x1f530);
		sEmojisMap.put(0x26fd, 0x26fd);
		sEmojisMap.put(0x1f3ee, 0x1f3ee);
		sEmojisMap.put(0x1f3b0, 0x1f3b0);
		sEmojisMap.put(0x2668, 0x2668);
		sEmojisMap.put(0x1f5ff, 0x1f5ff);
		sEmojisMap.put(0x1f3aa, 0x1f3aa);
		sEmojisMap.put(0x1f3ad, 0x1f3ad);
		sEmojisMap.put(0x1f4cd, 0x1f4cd);
		sEmojisMap.put(0x1f6a9, 0x1f6a9);
		// Emoji.fromChars("\ud83c\uddef\ud83c\uddf5");
		// Emoji.fromChars("\ud83c\uddf0\ud83c\uddf7");
		// Emoji.fromChars("\ud83c\udde9\ud83c\uddea");
		// Emoji.fromChars("\ud83c\udde8\ud83c\uddf3");
		// Emoji.fromChars("\ud83c\uddfa\ud83c\uddf8");
		// Emoji.fromChars("\ud83c\uddeb\ud83c\uddf7");
		// Emoji.fromChars("\ud83c\uddea\ud83c\uddf8");
		// Emoji.fromChars("\ud83c\uddee\ud83c\uddf9");
		// Emoji.fromChars("\ud83c\uddf7\ud83c\uddfa");
		// Emoji.fromChars("\ud83c\uddec\ud83c\udde7");

		// Symbols
		// Emoji.fromChars("\u0031\u20e3"),
		// Emoji.fromChars("\u0032\u20e3"),
		// Emoji.fromChars("\u0033\u20e3"),
		// Emoji.fromChars("\u0034\u20e3"),
		// Emoji.fromChars("\u0035\u20e3"),
		// Emoji.fromChars("\u0036\u20e3"),
		// Emoji.fromChars("\u0037\u20e3"),
		// Emoji.fromChars("\u0038\u20e3"),
		// Emoji.fromChars("\u0039\u20e3"),
		// Emoji.fromChars("\u0030\u20e3"),
		sEmojisMap.put(0x1f51f, 0x1f51f);
		sEmojisMap.put(0x1f522, 0x1f522);
		// Emoji.fromChars("\u0023\u20e3"),
		sEmojisMap.put(0x1f523, 0x1f523);
		sEmojisMap.put(0x2b06, 0x2b06);
		sEmojisMap.put(0x2b07, 0x2b07);
		sEmojisMap.put(0x2b05, 0x2b05);
		sEmojisMap.put(0x27a1, 0x27a1);
		sEmojisMap.put(0x1f520, 0x1f520);
		sEmojisMap.put(0x1f521, 0x1f521);
		sEmojisMap.put(0x1f524, 0x1f524);
		sEmojisMap.put(0x2197, 0x2197);
		sEmojisMap.put(0x2196, 0x2196);
		sEmojisMap.put(0x2198, 0x2198);
		sEmojisMap.put(0x2199, 0x2199);
		sEmojisMap.put(0x2194, 0x2194);
		sEmojisMap.put(0x2195, 0x2195);
		sEmojisMap.put(0x1f504, 0x1f504);
		sEmojisMap.put(0x25c0, 0x25c0);
		sEmojisMap.put(0x25b6, 0x25b6);
		sEmojisMap.put(0x1f53c, 0x1f53c);
		sEmojisMap.put(0x1f53d, 0x1f53d);
		sEmojisMap.put(0x21a9, 0x21a9);
		sEmojisMap.put(0x21aa, 0x21aa);
		sEmojisMap.put(0x2139, 0x2139);
		sEmojisMap.put(0x23ea, 0x23ea);
		sEmojisMap.put(0x23e9, 0x23e9);
		sEmojisMap.put(0x23eb, 0x23eb);
		sEmojisMap.put(0x23ec, 0x23ec);
		sEmojisMap.put(0x2935, 0x2935);
		sEmojisMap.put(0x2934, 0x2934);
		sEmojisMap.put(0x1f197, 0x1f197);
		sEmojisMap.put(0x1f500, 0x1f500);
		sEmojisMap.put(0x1f501, 0x1f501);
		sEmojisMap.put(0x1f502, 0x1f502);
		sEmojisMap.put(0x1f195, 0x1f195);
		sEmojisMap.put(0x1f199, 0x1f199);
		sEmojisMap.put(0x1f192, 0x1f192);
		sEmojisMap.put(0x1f193, 0x1f193);
		sEmojisMap.put(0x1f196, 0x1f196);
		sEmojisMap.put(0x1f4f6, 0x1f4f6);
		sEmojisMap.put(0x1f3a6, 0x1f3a6);
		sEmojisMap.put(0x1f201, 0x1f201);
		sEmojisMap.put(0x1f22f, 0x1f22f);
		sEmojisMap.put(0x1f233, 0x1f233);
		sEmojisMap.put(0x1f235, 0x1f235);
		sEmojisMap.put(0x1f234, 0x1f234);
		sEmojisMap.put(0x1f232, 0x1f232);
		sEmojisMap.put(0x1f250, 0x1f250);
		sEmojisMap.put(0x1f239, 0x1f239);
		sEmojisMap.put(0x1f23a, 0x1f23a);
		sEmojisMap.put(0x1f236, 0x1f236);
		sEmojisMap.put(0x1f21a, 0x1f21a);
		sEmojisMap.put(0x1f6bb, 0x1f6bb);
		sEmojisMap.put(0x1f6b9, 0x1f6b9);
		sEmojisMap.put(0x1f6ba, 0x1f6ba);
		sEmojisMap.put(0x1f6bc, 0x1f6bc);
		sEmojisMap.put(0x1f6be, 0x1f6be);
		sEmojisMap.put(0x1f6b0, 0x1f6b0);
		sEmojisMap.put(0x1f6ae, 0x1f6ae);
		sEmojisMap.put(0x1f17f, 0x1f17f);
		sEmojisMap.put(0x267f, 0x267f);
		sEmojisMap.put(0x1f6ad, 0x1f6ad);
		sEmojisMap.put(0x1f237, 0x1f237);
		sEmojisMap.put(0x1f238, 0x1f238);
		sEmojisMap.put(0x1f202, 0x1f202);
		sEmojisMap.put(0x24c2, 0x24c2);
		sEmojisMap.put(0x1f6c2, 0x1f6c2);
		sEmojisMap.put(0x1f6c4, 0x1f6c4);
		sEmojisMap.put(0x1f6c5, 0x1f6c5);
		sEmojisMap.put(0x1f6c3, 0x1f6c3);
		sEmojisMap.put(0x1f251, 0x1f251);
		sEmojisMap.put(0x3299, 0x3299);
		sEmojisMap.put(0x3297, 0x3297);
		sEmojisMap.put(0x1f191, 0x1f191);
		sEmojisMap.put(0x1f198, 0x1f198);
		sEmojisMap.put(0x1f194, 0x1f194);
		sEmojisMap.put(0x1f6ab, 0x1f6ab);
		sEmojisMap.put(0x1f51e, 0x1f51e);
		sEmojisMap.put(0x1f4f5, 0x1f4f5);
		sEmojisMap.put(0x1f6af, 0x1f6af);
		sEmojisMap.put(0x1f6b1, 0x1f6b1);
		sEmojisMap.put(0x1f6b3, 0x1f6b3);
		sEmojisMap.put(0x1f6b7, 0x1f6b7);
		sEmojisMap.put(0x1f6b8, 0x1f6b8);
		sEmojisMap.put(0x26d4, 0x26d4);
		sEmojisMap.put(0x2733, 0x2733);
		sEmojisMap.put(0x2747, 0x2747);
		sEmojisMap.put(0x274e, 0x274e);
		sEmojisMap.put(0x2705, 0x2705);
		sEmojisMap.put(0x2734, 0x2734);
		sEmojisMap.put(0x1f49f, 0x1f49f);
		sEmojisMap.put(0x1f19a, 0x1f19a);
		sEmojisMap.put(0x1f4f3, 0x1f4f3);
		sEmojisMap.put(0x1f4f4, 0x1f4f4);
		sEmojisMap.put(0x1f170, 0x1f170);
		sEmojisMap.put(0x1f171, 0x1f171);
		sEmojisMap.put(0x1f18e, 0x1f18e);
		sEmojisMap.put(0x1f17e, 0x1f17e);
		sEmojisMap.put(0x1f4a0, 0x1f4a0);
		sEmojisMap.put(0x27bf, 0x27bf);
		sEmojisMap.put(0x267b, 0x267b);
		sEmojisMap.put(0x2648, 0x2648);
		sEmojisMap.put(0x2649, 0x2649);
		sEmojisMap.put(0x264a, 0x264a);
		sEmojisMap.put(0x264b, 0x264b);
		sEmojisMap.put(0x264c, 0x264c);
		sEmojisMap.put(0x264d, 0x264d);
		sEmojisMap.put(0x264e, 0x264e);
		sEmojisMap.put(0x264f, 0x264f);
		sEmojisMap.put(0x2650, 0x2650);
		sEmojisMap.put(0x2651, 0x2651);
		sEmojisMap.put(0x2652, 0x2652);
		sEmojisMap.put(0x2653, 0x2653);
		sEmojisMap.put(0x26ce, 0x26ce);
		sEmojisMap.put(0x1f52f, 0x1f52f);
		sEmojisMap.put(0x1f3e7, 0x1f3e7);
		sEmojisMap.put(0x1f4b9, 0x1f4b9);
		sEmojisMap.put(0x1f4b2, 0x1f4b2);
		sEmojisMap.put(0x1f4b1, 0x1f4b1);
		sEmojisMap.put(0x00a9, 0x00a9);
		sEmojisMap.put(0x00ae, 0x00ae);
		sEmojisMap.put(0x2122, 0x2122);
		sEmojisMap.put(0x274c, 0x274c);
		sEmojisMap.put(0x203c, 0x203c);
		sEmojisMap.put(0x2049, 0x2049);
		sEmojisMap.put(0x2757, 0x2757);
		sEmojisMap.put(0x2753, 0x2753);
		sEmojisMap.put(0x2755, 0x2755);
		sEmojisMap.put(0x2754, 0x2754);
		sEmojisMap.put(0x2b55, 0x2b55);
		sEmojisMap.put(0x1f51d, 0x1f51d);
		sEmojisMap.put(0x1f51a, 0x1f51a);
		sEmojisMap.put(0x1f519, 0x1f519);
		sEmojisMap.put(0x1f51b, 0x1f51b);
		sEmojisMap.put(0x1f51c, 0x1f51c);
		sEmojisMap.put(0x1f503, 0x1f503);
		sEmojisMap.put(0x1f55b, 0x1f55b);
		sEmojisMap.put(0x1f567, 0x1f567);
		sEmojisMap.put(0x1f550, 0x1f550);
		sEmojisMap.put(0x1f55c, 0x1f55c);
		sEmojisMap.put(0x1f551, 0x1f551);
		sEmojisMap.put(0x1f55d, 0x1f55d);
		sEmojisMap.put(0x1f552, 0x1f552);
		sEmojisMap.put(0x1f55e, 0x1f55e);
		sEmojisMap.put(0x1f553, 0x1f553);
		sEmojisMap.put(0x1f55f, 0x1f55f);
		sEmojisMap.put(0x1f554, 0x1f554);
		sEmojisMap.put(0x1f560, 0x1f560);
		sEmojisMap.put(0x1f555, 0x1f555);
		sEmojisMap.put(0x1f556, 0x1f556);
		sEmojisMap.put(0x1f557, 0x1f557);
		sEmojisMap.put(0x1f558, 0x1f558);
		sEmojisMap.put(0x1f559, 0x1f559);
		sEmojisMap.put(0x1f55a, 0x1f55a);
		sEmojisMap.put(0x1f561, 0x1f561);
		sEmojisMap.put(0x1f562, 0x1f562);
		sEmojisMap.put(0x1f563, 0x1f563);
		sEmojisMap.put(0x1f564, 0x1f564);
		sEmojisMap.put(0x1f565, 0x1f565);
		sEmojisMap.put(0x1f566, 0x1f566);
		sEmojisMap.put(0x2716, 0x2716);
		sEmojisMap.put(0x2795, 0x2795);
		sEmojisMap.put(0x2796, 0x2796);
		sEmojisMap.put(0x2797, 0x2797);
		sEmojisMap.put(0x2660, 0x2660);
		sEmojisMap.put(0x2665, 0x2665);
		sEmojisMap.put(0x2663, 0x2663);
		sEmojisMap.put(0x2666, 0x2666);
		sEmojisMap.put(0x1f4ae, 0x1f4ae);
		sEmojisMap.put(0x1f4af, 0x1f4af);
		sEmojisMap.put(0x2714, 0x2714);
		sEmojisMap.put(0x2611, 0x2611);
		sEmojisMap.put(0x1f518, 0x1f518);
		sEmojisMap.put(0x1f517, 0x1f517);
		sEmojisMap.put(0x27b0, 0x27b0);
		sEmojisMap.put(0x3030, 0x3030);
		sEmojisMap.put(0x303d, 0x303d);
		sEmojisMap.put(0x1f531, 0x1f531);
		sEmojisMap.put(0x25fc, 0x25fc);
		sEmojisMap.put(0x25fb, 0x25fb);
		sEmojisMap.put(0x25fe, 0x25fe);
		sEmojisMap.put(0x25fd, 0x25fd);
		sEmojisMap.put(0x25aa, 0x25aa);
		sEmojisMap.put(0x25ab, 0x25ab);
		sEmojisMap.put(0x1f53a, 0x1f53a);
		sEmojisMap.put(0x1f532, 0x1f532);
		sEmojisMap.put(0x1f533, 0x1f533);
		sEmojisMap.put(0x26ab, 0x26ab);
		sEmojisMap.put(0x26aa, 0x26aa);
		sEmojisMap.put(0x1f534, 0x1f534);
		sEmojisMap.put(0x1f535, 0x1f535);
		sEmojisMap.put(0x1f53b, 0x1f53b);
		sEmojisMap.put(0x2b1c, 0x2b1c);
		sEmojisMap.put(0x2b1b, 0x2b1b);
		sEmojisMap.put(0x1f536, 0x1f536);
		sEmojisMap.put(0x1f537, 0x1f537);
		sEmojisMap.put(0x1f538, 0x1f538);
		sEmojisMap.put(0x1f539, 0x1f539);

		sSoftbanksMap.put(0xe001, 0x1f466);
		sSoftbanksMap.put(0xe002, 0x1f467);
		sSoftbanksMap.put(0xe003, 0x1f48b);
		sSoftbanksMap.put(0xe004, 0x1f468);
		sSoftbanksMap.put(0xe005, 0x1f469);
		sSoftbanksMap.put(0xe006, 0x1f455);
		sSoftbanksMap.put(0xe007, 0x1f45e);
		sSoftbanksMap.put(0xe008, 0x1f4f7);
		sSoftbanksMap.put(0xe009, 0x1f4de);
		sSoftbanksMap.put(0xe00a, 0x1f4f1);
		sSoftbanksMap.put(0xe00b, 0x1f4e0);
		sSoftbanksMap.put(0xe00c, 0x1f4bb);
		sSoftbanksMap.put(0xe00d, 0x1f44a);
		sSoftbanksMap.put(0xe00e, 0x1f44d);
		sSoftbanksMap.put(0xe00f, 0x261d);
		sSoftbanksMap.put(0xe010, 0x270a);
		sSoftbanksMap.put(0xe011, 0x270c);
		sSoftbanksMap.put(0xe012, 0x1f64b);
		sSoftbanksMap.put(0xe013, 0x1f3bf);
		sSoftbanksMap.put(0xe014, 0x26f3);
		sSoftbanksMap.put(0xe015, 0x1f3be);
		sSoftbanksMap.put(0xe016, 0x26be);
		sSoftbanksMap.put(0xe017, 0x1f3c4);
		sSoftbanksMap.put(0xe018, 0x26bd);
		sSoftbanksMap.put(0xe019, 0x1f3a3);
		sSoftbanksMap.put(0xe01a, 0x1f434);
		sSoftbanksMap.put(0xe01b, 0x1f697);
		sSoftbanksMap.put(0xe01c, 0x26f5);
		sSoftbanksMap.put(0xe01d, 0x2708);
		sSoftbanksMap.put(0xe01e, 0x1f683);
		sSoftbanksMap.put(0xe01f, 0x1f685);
		sSoftbanksMap.put(0xe020, 0x2753);
		sSoftbanksMap.put(0xe021, 0x2757);
		sSoftbanksMap.put(0xe022, 0x2764);
		sSoftbanksMap.put(0xe023, 0x1f494);
		sSoftbanksMap.put(0xe024, 0x1f550);
		sSoftbanksMap.put(0xe025, 0x1f551);
		sSoftbanksMap.put(0xe026, 0x1f552);
		sSoftbanksMap.put(0xe027, 0x1f553);
		sSoftbanksMap.put(0xe028, 0x1f554);
		sSoftbanksMap.put(0xe029, 0x1f555);
		sSoftbanksMap.put(0xe02a, 0x1f556);
		sSoftbanksMap.put(0xe02b, 0x1f557);
		sSoftbanksMap.put(0xe02c, 0x1f558);
		sSoftbanksMap.put(0xe02d, 0x1f559);
		sSoftbanksMap.put(0xe02e, 0x1f55a);
		sSoftbanksMap.put(0xe02f, 0x1f55b);
		sSoftbanksMap.put(0xe030, 0x1f338);
		sSoftbanksMap.put(0xe031, 0x1f531);
		sSoftbanksMap.put(0xe032, 0x1f339);
		sSoftbanksMap.put(0xe033, 0x1f384);
		sSoftbanksMap.put(0xe034, 0x1f48d);
		sSoftbanksMap.put(0xe035, 0x1f48e);
		sSoftbanksMap.put(0xe036, 0x1f3e0);
		sSoftbanksMap.put(0xe037, 0x26ea);
		sSoftbanksMap.put(0xe038, 0x1f3e2);
		sSoftbanksMap.put(0xe039, 0x1f689);
		sSoftbanksMap.put(0xe03a, 0x26fd);
		sSoftbanksMap.put(0xe03b, 0x1f5fb);
		sSoftbanksMap.put(0xe03c, 0x1f3a4);
		sSoftbanksMap.put(0xe03d, 0x1f3a5);
		sSoftbanksMap.put(0xe03e, 0x1f3b5);
		sSoftbanksMap.put(0xe03f, 0x1f511);
		sSoftbanksMap.put(0xe040, 0x1f3b7);
		sSoftbanksMap.put(0xe041, 0x1f3b8);
		sSoftbanksMap.put(0xe042, 0x1f3ba);
		sSoftbanksMap.put(0xe043, 0x1f374);
		sSoftbanksMap.put(0xe044, 0x1f377);
		sSoftbanksMap.put(0xe045, 0x2615);
		sSoftbanksMap.put(0xe046, 0x1f370);
		sSoftbanksMap.put(0xe047, 0x1f37a);
		sSoftbanksMap.put(0xe048, 0x26c4);
		sSoftbanksMap.put(0xe049, 0x2601);
		sSoftbanksMap.put(0xe04a, 0x2600);
		sSoftbanksMap.put(0xe04b, 0x2614);
		sSoftbanksMap.put(0xe04c, 0x1f313);
		sSoftbanksMap.put(0xe04d, 0x1f304);
		sSoftbanksMap.put(0xe04e, 0x1f47c);
		sSoftbanksMap.put(0xe04f, 0x1f431);
		sSoftbanksMap.put(0xe050, 0x1f42f);
		sSoftbanksMap.put(0xe051, 0x1f43b);
		sSoftbanksMap.put(0xe052, 0x1f429);
		sSoftbanksMap.put(0xe053, 0x1f42d);
		sSoftbanksMap.put(0xe054, 0x1f433);
		sSoftbanksMap.put(0xe055, 0x1f427);
		sSoftbanksMap.put(0xe056, 0x1f60a);
		sSoftbanksMap.put(0xe057, 0x1f603);
		sSoftbanksMap.put(0xe058, 0x1f61e);
		sSoftbanksMap.put(0xe059, 0x1f620);
		sSoftbanksMap.put(0xe05a, 0x1f4a9);
		sSoftbanksMap.put(0xe101, 0x1f4ea);
		sSoftbanksMap.put(0xe102, 0x1f4ee);
		sSoftbanksMap.put(0xe103, 0x1f4e7);
		sSoftbanksMap.put(0xe104, 0x1f4f2);
		sSoftbanksMap.put(0xe105, 0x1f61c);
		sSoftbanksMap.put(0xe106, 0x1f60d);
		sSoftbanksMap.put(0xe107, 0x1f631);
		sSoftbanksMap.put(0xe108, 0x1f613);
		sSoftbanksMap.put(0xe109, 0x1f435);
		sSoftbanksMap.put(0xe10a, 0x1f419);
		sSoftbanksMap.put(0xe10b, 0x1f437);
		sSoftbanksMap.put(0xe10c, 0x1f47d);
		sSoftbanksMap.put(0xe10d, 0x1f680);
		sSoftbanksMap.put(0xe10e, 0x1f451);
		sSoftbanksMap.put(0xe10f, 0x1f4a1);
		sSoftbanksMap.put(0xe110, 0x1f331);
		sSoftbanksMap.put(0xe111, 0x1f48f);
		sSoftbanksMap.put(0xe112, 0x1f381);
		sSoftbanksMap.put(0xe113, 0x1f52b);
		sSoftbanksMap.put(0xe114, 0x1f50d);
		sSoftbanksMap.put(0xe115, 0x1f3c3);
		sSoftbanksMap.put(0xe116, 0x1f528);
		sSoftbanksMap.put(0xe117, 0x1f386);
		sSoftbanksMap.put(0xe118, 0x1f341);
		sSoftbanksMap.put(0xe119, 0x1f342);
		sSoftbanksMap.put(0xe11a, 0x1f47f);
		sSoftbanksMap.put(0xe11b, 0x1f47b);
		sSoftbanksMap.put(0xe11c, 0x1f480);
		sSoftbanksMap.put(0xe11d, 0x1f525);
		sSoftbanksMap.put(0xe11e, 0x1f4bc);
		sSoftbanksMap.put(0xe11f, 0x1f4ba);
		sSoftbanksMap.put(0xe120, 0x1f354);
		sSoftbanksMap.put(0xe121, 0x26f2);
		sSoftbanksMap.put(0xe122, 0x26fa);
		sSoftbanksMap.put(0xe123, 0x2668);
		sSoftbanksMap.put(0xe124, 0x1f3a1);
		sSoftbanksMap.put(0xe125, 0x1f3ab);
		sSoftbanksMap.put(0xe126, 0x1f4bf);
		sSoftbanksMap.put(0xe127, 0x1f4c0);
		sSoftbanksMap.put(0xe128, 0x1f4fb);
		sSoftbanksMap.put(0xe129, 0x1f4fc);
		sSoftbanksMap.put(0xe12a, 0x1f4fa);
		sSoftbanksMap.put(0xe12b, 0x1f47e);
		sSoftbanksMap.put(0xe12c, 0x303d);
		sSoftbanksMap.put(0xe12d, 0x1f004);
		sSoftbanksMap.put(0xe12e, 0x1f19a);
		sSoftbanksMap.put(0xe12f, 0x1f4b0);
		sSoftbanksMap.put(0xe130, 0x1f3af);
		sSoftbanksMap.put(0xe131, 0x1f3c6);
		sSoftbanksMap.put(0xe132, 0x1f3c1);
		sSoftbanksMap.put(0xe133, 0x1f3b0);
		sSoftbanksMap.put(0xe134, 0x1f40e);
		sSoftbanksMap.put(0xe135, 0x1f6a4);
		sSoftbanksMap.put(0xe136, 0x1f6b2);
		sSoftbanksMap.put(0xe137, 0x1f6a7);
		sSoftbanksMap.put(0xe138, 0x1f6b9);
		sSoftbanksMap.put(0xe139, 0x1f6ba);
		sSoftbanksMap.put(0xe13a, 0x1f6bc);
		sSoftbanksMap.put(0xe13b, 0x1f489);
		sSoftbanksMap.put(0xe13c, 0x1f4a4);
		sSoftbanksMap.put(0xe13d, 0x26a1);
		sSoftbanksMap.put(0xe13e, 0x1f460);
		sSoftbanksMap.put(0xe13f, 0x1f6c0);
		sSoftbanksMap.put(0xe140, 0x1f6bd);
		sSoftbanksMap.put(0xe141, 0x1f50a);
		sSoftbanksMap.put(0xe142, 0x1f4e2);
		sSoftbanksMap.put(0xe143, 0x1f38c);
		sSoftbanksMap.put(0xe144, 0x1f50f);
		sSoftbanksMap.put(0xe145, 0x1f513);
		sSoftbanksMap.put(0xe146, 0x1f306);
		sSoftbanksMap.put(0xe147, 0x1f373);
		sSoftbanksMap.put(0xe148, 0x1f4c7);
		sSoftbanksMap.put(0xe149, 0x1f4b1);
		sSoftbanksMap.put(0xe14a, 0x1f4b9);
		sSoftbanksMap.put(0xe14b, 0x1f4e1);
		sSoftbanksMap.put(0xe14c, 0x1f4aa);
		sSoftbanksMap.put(0xe14d, 0x1f3e6);
		sSoftbanksMap.put(0xe14e, 0x1f6a5);
		sSoftbanksMap.put(0xe14f, 0x1f17f);
		sSoftbanksMap.put(0xe150, 0x1f68f);
		sSoftbanksMap.put(0xe151, 0x1f6bb);
		sSoftbanksMap.put(0xe152, 0x1f46e);
		sSoftbanksMap.put(0xe153, 0x1f3e3);
		sSoftbanksMap.put(0xe154, 0x1f3e7);
		sSoftbanksMap.put(0xe155, 0x1f3e5);
		sSoftbanksMap.put(0xe156, 0x1f3ea);
		sSoftbanksMap.put(0xe157, 0x1f3eb);
		sSoftbanksMap.put(0xe158, 0x1f3e8);
		sSoftbanksMap.put(0xe159, 0x1f68c);
		sSoftbanksMap.put(0xe15a, 0x1f695);
		sSoftbanksMap.put(0xe201, 0x1f6b6);
		sSoftbanksMap.put(0xe202, 0x1f6a2);
		sSoftbanksMap.put(0xe203, 0x1f201);
		sSoftbanksMap.put(0xe204, 0x1f49f);
		sSoftbanksMap.put(0xe205, 0x2734);
		sSoftbanksMap.put(0xe206, 0x2733);
		sSoftbanksMap.put(0xe207, 0x1f51e);
		sSoftbanksMap.put(0xe208, 0x1f6ad);
		sSoftbanksMap.put(0xe209, 0x1f530);
		sSoftbanksMap.put(0xe20a, 0x267f);
		sSoftbanksMap.put(0xe20b, 0x1f4f6);
		sSoftbanksMap.put(0xe20c, 0x2665);
		sSoftbanksMap.put(0xe20d, 0x2666);
		sSoftbanksMap.put(0xe20e, 0x2660);
		sSoftbanksMap.put(0xe20f, 0x2663);
		sSoftbanksMap.put(0xe210, 0x0023);
		sSoftbanksMap.put(0xe211, 0x27bf);
		sSoftbanksMap.put(0xe212, 0x1f195);
		sSoftbanksMap.put(0xe213, 0x1f199);
		sSoftbanksMap.put(0xe214, 0x1f192);
		sSoftbanksMap.put(0xe215, 0x1f236);
		sSoftbanksMap.put(0xe216, 0x1f21a);
		sSoftbanksMap.put(0xe217, 0x1f237);
		sSoftbanksMap.put(0xe218, 0x1f238);
		sSoftbanksMap.put(0xe219, 0x1f534);
		sSoftbanksMap.put(0xe21a, 0x1f532);
		sSoftbanksMap.put(0xe21b, 0x1f533);
		sSoftbanksMap.put(0xe21c, 0x0031);
		sSoftbanksMap.put(0xe21d, 0x0032);
		sSoftbanksMap.put(0xe21e, 0x0033);
		sSoftbanksMap.put(0xe21f, 0x0034);
		sSoftbanksMap.put(0xe220, 0x0035);
		sSoftbanksMap.put(0xe221, 0x0036);
		sSoftbanksMap.put(0xe222, 0x0037);
		sSoftbanksMap.put(0xe223, 0x0038);
		sSoftbanksMap.put(0xe224, 0x0039);
		sSoftbanksMap.put(0xe225, 0x0030);
		sSoftbanksMap.put(0xe226, 0x1f250);
		sSoftbanksMap.put(0xe227, 0x1f239);
		sSoftbanksMap.put(0xe228, 0x1f202);
		sSoftbanksMap.put(0xe229, 0x1f194);
		sSoftbanksMap.put(0xe22a, 0x1f235);
		sSoftbanksMap.put(0xe22b, 0x1f233);
		sSoftbanksMap.put(0xe22c, 0x1f22f);
		sSoftbanksMap.put(0xe22d, 0x1f23a);
		sSoftbanksMap.put(0xe22e, 0x1f446);
		sSoftbanksMap.put(0xe22f, 0x1f447);
		sSoftbanksMap.put(0xe230, 0x1f448);
		sSoftbanksMap.put(0xe231, 0x1f449);
		sSoftbanksMap.put(0xe232, 0x2b06);
		sSoftbanksMap.put(0xe233, 0x2b07);
		sSoftbanksMap.put(0xe234, 0x27a1);
		sSoftbanksMap.put(0xe235, 0x1f519);
		sSoftbanksMap.put(0xe236, 0x2197);
		sSoftbanksMap.put(0xe237, 0x2196);
		sSoftbanksMap.put(0xe238, 0x2198);
		sSoftbanksMap.put(0xe239, 0x2199);
		sSoftbanksMap.put(0xe23a, 0x25b6);
		sSoftbanksMap.put(0xe23b, 0x25c0);
		sSoftbanksMap.put(0xe23c, 0x23e9);
		sSoftbanksMap.put(0xe23d, 0x23ea);
		sSoftbanksMap.put(0xe23e, 0x1f52e);
		sSoftbanksMap.put(0xe23f, 0x2648);
		sSoftbanksMap.put(0xe240, 0x2649);
		sSoftbanksMap.put(0xe241, 0x264a);
		sSoftbanksMap.put(0xe242, 0x264b);
		sSoftbanksMap.put(0xe243, 0x264c);
		sSoftbanksMap.put(0xe244, 0x264d);
		sSoftbanksMap.put(0xe245, 0x264e);
		sSoftbanksMap.put(0xe246, 0x264f);
		sSoftbanksMap.put(0xe247, 0x2650);
		sSoftbanksMap.put(0xe248, 0x2651);
		sSoftbanksMap.put(0xe249, 0x2652);
		sSoftbanksMap.put(0xe24a, 0x2653);
		sSoftbanksMap.put(0xe24b, 0x26ce);
		sSoftbanksMap.put(0xe24c, 0x1f51d);
		sSoftbanksMap.put(0xe24d, 0x1f197);
		sSoftbanksMap.put(0xe24e, 0x00a9);
		sSoftbanksMap.put(0xe24f, 0x00ae);
		sSoftbanksMap.put(0xe250, 0x1f4f3);
		sSoftbanksMap.put(0xe251, 0x1f4f4);
		sSoftbanksMap.put(0xe252, 0x26a0);
		sSoftbanksMap.put(0xe253, 0x1f481);
		sSoftbanksMap.put(0xe301, 0x1f4c3);
		sSoftbanksMap.put(0xe302, 0x1f454);
		sSoftbanksMap.put(0xe303, 0x1f33a);
		sSoftbanksMap.put(0xe304, 0x1f337);
		sSoftbanksMap.put(0xe305, 0x1f33b);
		sSoftbanksMap.put(0xe306, 0x1f490);
		sSoftbanksMap.put(0xe307, 0x1f334);
		sSoftbanksMap.put(0xe308, 0x1f335);
		sSoftbanksMap.put(0xe309, 0x1f6be);
		sSoftbanksMap.put(0xe30a, 0x1f3a7);
		sSoftbanksMap.put(0xe30b, 0x1f376);
		sSoftbanksMap.put(0xe30c, 0x1f37b);
		sSoftbanksMap.put(0xe30d, 0x3297);
		sSoftbanksMap.put(0xe30e, 0x1f6ac);
		sSoftbanksMap.put(0xe30f, 0x1f48a);
		sSoftbanksMap.put(0xe310, 0x1f388);
		sSoftbanksMap.put(0xe311, 0x1f4a3);
		sSoftbanksMap.put(0xe312, 0x1f389);
		sSoftbanksMap.put(0xe313, 0x2702);
		sSoftbanksMap.put(0xe314, 0x1f380);
		sSoftbanksMap.put(0xe315, 0x3299);
		sSoftbanksMap.put(0xe316, 0x1f4bd);
		sSoftbanksMap.put(0xe317, 0x1f4e3);
		sSoftbanksMap.put(0xe318, 0x1f452);
		sSoftbanksMap.put(0xe319, 0x1f457);
		sSoftbanksMap.put(0xe31a, 0x1f461);
		sSoftbanksMap.put(0xe31b, 0x1f462);
		sSoftbanksMap.put(0xe31c, 0x1f484);
		sSoftbanksMap.put(0xe31d, 0x1f485);
		sSoftbanksMap.put(0xe31e, 0x1f486);
		sSoftbanksMap.put(0xe31f, 0x1f487);
		sSoftbanksMap.put(0xe320, 0x1f488);
		sSoftbanksMap.put(0xe321, 0x1f458);
		sSoftbanksMap.put(0xe322, 0x1f459);
		sSoftbanksMap.put(0xe323, 0x1f45c);
		sSoftbanksMap.put(0xe324, 0x1f3ac);
		sSoftbanksMap.put(0xe325, 0x1f514);
		sSoftbanksMap.put(0xe326, 0x1f3b6);
		sSoftbanksMap.put(0xe327, 0x1f493);
		sSoftbanksMap.put(0xe328, 0x1f48c);
		sSoftbanksMap.put(0xe329, 0x1f498);
		sSoftbanksMap.put(0xe32a, 0x1f499);
		sSoftbanksMap.put(0xe32b, 0x1f49a);
		sSoftbanksMap.put(0xe32c, 0x1f49b);
		sSoftbanksMap.put(0xe32d, 0x1f49c);
		sSoftbanksMap.put(0xe32e, 0x2728);
		sSoftbanksMap.put(0xe32f, 0x2b50);
		sSoftbanksMap.put(0xe330, 0x1f4a8);
		sSoftbanksMap.put(0xe331, 0x1f4a6);
		sSoftbanksMap.put(0xe332, 0x2b55);
		sSoftbanksMap.put(0xe333, 0x2716);
		sSoftbanksMap.put(0xe334, 0x1f4a2);
		sSoftbanksMap.put(0xe335, 0x1f31f);
		sSoftbanksMap.put(0xe336, 0x2754);
		sSoftbanksMap.put(0xe337, 0x2755);
		sSoftbanksMap.put(0xe338, 0x1f375);
		sSoftbanksMap.put(0xe339, 0x1f35e);
		sSoftbanksMap.put(0xe33a, 0x1f366);
		sSoftbanksMap.put(0xe33b, 0x1f35f);
		sSoftbanksMap.put(0xe33c, 0x1f361);
		sSoftbanksMap.put(0xe33d, 0x1f358);
		sSoftbanksMap.put(0xe33e, 0x1f35a);
		sSoftbanksMap.put(0xe33f, 0x1f35d);
		sSoftbanksMap.put(0xe340, 0x1f35c);
		sSoftbanksMap.put(0xe341, 0x1f35b);
		sSoftbanksMap.put(0xe342, 0x1f359);
		sSoftbanksMap.put(0xe343, 0x1f362);
		sSoftbanksMap.put(0xe344, 0x1f363);
		sSoftbanksMap.put(0xe345, 0x1f34e);
		sSoftbanksMap.put(0xe346, 0x1f34a);
		sSoftbanksMap.put(0xe347, 0x1f353);
		sSoftbanksMap.put(0xe348, 0x1f349);
		sSoftbanksMap.put(0xe349, 0x1f345);
		sSoftbanksMap.put(0xe34a, 0x1f346);
		sSoftbanksMap.put(0xe34b, 0x1f382);
		sSoftbanksMap.put(0xe34c, 0x1f371);
		sSoftbanksMap.put(0xe34d, 0x1f372);
		sSoftbanksMap.put(0xe401, 0x1f625);
		sSoftbanksMap.put(0xe402, 0x1f60f);
		sSoftbanksMap.put(0xe403, 0x1f614);
		sSoftbanksMap.put(0xe404, 0x1f601);
		sSoftbanksMap.put(0xe405, 0x1f609);
		sSoftbanksMap.put(0xe406, 0x1f623);
		sSoftbanksMap.put(0xe407, 0x1f616);
		sSoftbanksMap.put(0xe408, 0x1f62a);
		sSoftbanksMap.put(0xe409, 0x1f445);
		sSoftbanksMap.put(0xe40a, 0x1f606);
		sSoftbanksMap.put(0xe40b, 0x1f628);
		sSoftbanksMap.put(0xe40c, 0x1f637);
		sSoftbanksMap.put(0xe40d, 0x1f633);
		sSoftbanksMap.put(0xe40e, 0x1f612);
		sSoftbanksMap.put(0xe40f, 0x1f630);
		sSoftbanksMap.put(0xe410, 0x1f632);
		sSoftbanksMap.put(0xe411, 0x1f62d);
		sSoftbanksMap.put(0xe412, 0x1f602);
		sSoftbanksMap.put(0xe413, 0x1f622);
		sSoftbanksMap.put(0xe414, 0x263a);
		sSoftbanksMap.put(0xe415, 0x1f605);
		sSoftbanksMap.put(0xe416, 0x1f621);
		sSoftbanksMap.put(0xe417, 0x1f61a);
		sSoftbanksMap.put(0xe418, 0x1f618);
		sSoftbanksMap.put(0xe419, 0x1f440);
		sSoftbanksMap.put(0xe41a, 0x1f443);
		sSoftbanksMap.put(0xe41b, 0x1f442);
		sSoftbanksMap.put(0xe41c, 0x1f444);
		sSoftbanksMap.put(0xe41d, 0x1f64f);
		sSoftbanksMap.put(0xe41e, 0x1f44b);
		sSoftbanksMap.put(0xe41f, 0x1f44f);
		sSoftbanksMap.put(0xe420, 0x1f44c);
		sSoftbanksMap.put(0xe421, 0x1f44e);
		sSoftbanksMap.put(0xe422, 0x1f450);
		sSoftbanksMap.put(0xe423, 0x1f645);
		sSoftbanksMap.put(0xe424, 0x1f646);
		sSoftbanksMap.put(0xe425, 0x1f491);
		sSoftbanksMap.put(0xe426, 0x1f647);
		sSoftbanksMap.put(0xe427, 0x1f64c);
		sSoftbanksMap.put(0xe428, 0x1f46b);
		sSoftbanksMap.put(0xe429, 0x1f46f);
		sSoftbanksMap.put(0xe42a, 0x1f3c0);
		sSoftbanksMap.put(0xe42b, 0x1f3c8);
		sSoftbanksMap.put(0xe42c, 0x1f3b1);
		sSoftbanksMap.put(0xe42d, 0x1f3ca);
		sSoftbanksMap.put(0xe42e, 0x1f699);
		sSoftbanksMap.put(0xe42f, 0x1f69a);
		sSoftbanksMap.put(0xe430, 0x1f692);
		sSoftbanksMap.put(0xe431, 0x1f691);
		sSoftbanksMap.put(0xe432, 0x1f693);
		sSoftbanksMap.put(0xe433, 0x1f3a2);
		sSoftbanksMap.put(0xe434, 0x1f687);
		sSoftbanksMap.put(0xe435, 0x1f684);
		sSoftbanksMap.put(0xe436, 0x1f38d);
		sSoftbanksMap.put(0xe437, 0x1f49d);
		sSoftbanksMap.put(0xe438, 0x1f38e);
		sSoftbanksMap.put(0xe439, 0x1f393);
		sSoftbanksMap.put(0xe43a, 0x1f392);
		sSoftbanksMap.put(0xe43b, 0x1f38f);
		sSoftbanksMap.put(0xe43c, 0x1f302);
		sSoftbanksMap.put(0xe43d, 0x1f492);
		sSoftbanksMap.put(0xe43e, 0x1f30a);
		sSoftbanksMap.put(0xe43f, 0x1f367);
		sSoftbanksMap.put(0xe440, 0x1f387);
		sSoftbanksMap.put(0xe441, 0x1f41a);
		sSoftbanksMap.put(0xe442, 0x1f390);
		sSoftbanksMap.put(0xe443, 0x1f300);
		sSoftbanksMap.put(0xe444, 0x1f33e);
		sSoftbanksMap.put(0xe445, 0x1f383);
		sSoftbanksMap.put(0xe446, 0x1f391);
		sSoftbanksMap.put(0xe447, 0x1f343);
		sSoftbanksMap.put(0xe448, 0x1f385);
		sSoftbanksMap.put(0xe449, 0x1f305);
		sSoftbanksMap.put(0xe44a, 0x1f307);
		sSoftbanksMap.put(0xe44b, 0x1f303);
		sSoftbanksMap.put(0xe44b, 0x1f30c);
		sSoftbanksMap.put(0xe44c, 0x1f308);
		sSoftbanksMap.put(0xe501, 0x1f3e9);
		sSoftbanksMap.put(0xe502, 0x1f3a8);
		sSoftbanksMap.put(0xe503, 0x1f3a9);
		sSoftbanksMap.put(0xe504, 0x1f3ec);
		sSoftbanksMap.put(0xe505, 0x1f3ef);
		sSoftbanksMap.put(0xe506, 0x1f3f0);
		sSoftbanksMap.put(0xe507, 0x1f3a6);
		sSoftbanksMap.put(0xe508, 0x1f3ed);
		sSoftbanksMap.put(0xe509, 0x1f5fc);
		sSoftbanksMap.put(0xe50b, 0xe50b);
		sSoftbanksMap.put(0xe50c, 0xe50b);
		sSoftbanksMap.put(0xe50d, 0xe50d);
		sSoftbanksMap.put(0xe50e, 0xe50e);
		sSoftbanksMap.put(0xe50f, 0xe50f);
		sSoftbanksMap.put(0xe510, 0xe510);
		sSoftbanksMap.put(0xe511, 0xe511);
		sSoftbanksMap.put(0xe512, 0xe512);
		sSoftbanksMap.put(0xe513, 0xe513);
		sSoftbanksMap.put(0xe514, 0xe514);
		sSoftbanksMap.put(0xe515, 0x1f471);
		sSoftbanksMap.put(0xe516, 0x1f472);
		sSoftbanksMap.put(0xe517, 0x1f473);
		sSoftbanksMap.put(0xe518, 0x1f474);
		sSoftbanksMap.put(0xe519, 0x1f475);
		sSoftbanksMap.put(0xe51a, 0x1f476);
		sSoftbanksMap.put(0xe51b, 0x1f477);
		sSoftbanksMap.put(0xe51c, 0x1f478);
		sSoftbanksMap.put(0xe51d, 0x1f5fd);
		sSoftbanksMap.put(0xe51e, 0x1f482);
		sSoftbanksMap.put(0xe51f, 0x1f483);
		sSoftbanksMap.put(0xe520, 0x1f42c);
		sSoftbanksMap.put(0xe521, 0x1f426);
		sSoftbanksMap.put(0xe522, 0x1f420);
		sSoftbanksMap.put(0xe523, 0x1f423);
		sSoftbanksMap.put(0xe524, 0x1f439);
		sSoftbanksMap.put(0xe525, 0x1f41b);
		sSoftbanksMap.put(0xe526, 0x1f418);
		sSoftbanksMap.put(0xe527, 0x1f428);
		sSoftbanksMap.put(0xe528, 0x1f412);
		sSoftbanksMap.put(0xe529, 0x1f411);
		sSoftbanksMap.put(0xe52a, 0x1f43a);
		sSoftbanksMap.put(0xe52b, 0x1f42e);
		sSoftbanksMap.put(0xe52c, 0x1f430);
		sSoftbanksMap.put(0xe52d, 0x1f40d);
		sSoftbanksMap.put(0xe52e, 0x1f414);
		sSoftbanksMap.put(0xe52f, 0x1f417);
		sSoftbanksMap.put(0xe530, 0x1f42b);
		sSoftbanksMap.put(0xe531, 0x1f438);
		sSoftbanksMap.put(0xe532, 0x1f170);
		sSoftbanksMap.put(0xe533, 0x1f171);
		sSoftbanksMap.put(0xe534, 0x1f18e);
		sSoftbanksMap.put(0xe535, 0x1f17e);
		sSoftbanksMap.put(0xe536, 0x1f43e);
		sSoftbanksMap.put(0xe537, 0x2122);
	}

	/*private static boolean isSoftBankEmoji(char c)
	{
		return ((c >> 12) == 0xe);
	}

	private static int getEmojiResource(int codePoint)
	{
		return sEmojisMap.get(codePoint);
	}

	private static int getSoftbankEmojiResource(char c)
	{
		return sSoftbanksMap.get(c);
	}*/

	public static String replaceUBB(String text)
	{
		Pattern pattern = Pattern.compile("\\[emoji\\][0-9a-zA-Z]+,?[0-9a-zA-Z]*\\[/emoji\\]");
		Matcher matcher = pattern.matcher(text);
		String subStr = "";
		String encodeStr = "";
		String newStr = "";
		String[] subArr = new String[0];
		while(matcher.find())
		{
		/*	System.out.println(
			       						"Found \""+matcher.group()+"\" starting index "+matcher.start()+
			       						" ending index "+matcher.end()
			       						);*/
			subStr = matcher.group();
			encodeStr = subStr.substring(7, subStr.lastIndexOf("[/emoji]"));
			subArr = encodeStr.split(",");
			if(subArr.length==1)
				newStr = newString(Integer.parseInt(subArr[0], 16));
			else
				newStr = newString(Integer.parseInt(subArr[0], 16))+newString(Integer.parseInt(subArr[1], 16));
			
			//System.out.println(Arrays.toString(subArr));
			text = text.replace(matcher.group(), newStr);
		}
	//	System.out.println(text);
		return text;
	}
	
	public static String replaceUBBALL(String text)
	{
		return text.replaceAll("\\[emoji\\][0-9a-zA-Z]+,?[0-9a-zA-Z]*\\[/emoji\\]", "<表情>");
	}
	
	
	/**
	 * 将含有emoji的字符串替换成转义的UBB代码 例如 笑脸 \ud83d\ude04bc ==> [emoji]1f604[/emoji] 键盘数字1
	 * \u0031\u20e3 ==> [emoji]0031,20e3[/emoji] \u2b07 ==> [emoji]2b07[/emoji]
	 * \ud83c\uddef\ud83c\uddf5 ==> [emoji]1f1ef,1f1f5[/emoji]
	 * 
	 * @param text
	 */
	public static String replaceEmoji(String text)
	{
		StringBuffer newText = new StringBuffer("");
		char[] charArr = text.toCharArray();
		int len = charArr.length;
		int skip = 0;
		for (int i = 0; i < len; i += skip) // 循环遍历字符替换可能出现的表情
		{
			// 只考虑unicode unified的emoji表情编码
			int unicode = Character.codePointAt(charArr, i);
			skip = Character.charCount(unicode);
			//String replaceStr = "";
			boolean exists = false;
			if (unicode > 0xff && (exists = (sEmojisMap.indexOfKey(unicode) > 0)))
			{
					//if (skip == 1)
					//	replaceStr = Character.toString(charArr[i]);
					//else
					//	replaceStr = text.substring(i, i + 2);
					newText.append("[emoji]" + Integer.toHexString(unicode) + "[/emoji]");
			}
			else if (!exists && i + skip < len) // 自定义表情的处理
			{
				int followUnicode = Character.codePointAt(text, i + skip);
				if (followUnicode == 0x20e3)
				{
					int followSkip = Character.charCount(followUnicode);
					switch (unicode)
					{
					case 0x0031:
						newText.append("[emoji]0031,20e3[/emoji]");
						break;
					case 0x0032:
						newText.append("[emoji]0032,20e3[/emoji]");
						break;
					case 0x0033:
						newText.append("[emoji]0033,20e3[/emoji]");
						break;
					case 0x0034:
						newText.append("[emoji]0034,20e3[/emoji]");
						break;
					case 0x0035:
						newText.append("[emoji]0035,20e3[/emoji]");
						break;
					case 0x0036:
						newText.append("[emoji]0036,20e3[/emoji]");
						break;
					case 0x0037:
						newText.append("[emoji]0037,20e3[/emoji]");
						break;
					case 0x0038:
						newText.append("[emoji]0038,20e3[/emoji]");
						break;
					case 0x0039:
						newText.append("[emoji]0039,20e3[/emoji]");
						break;
					case 0x0030:
						newText.append("[emoji]0030,20e3[/emoji]");
						break;
					case 0x0023:
						newText.append("[emoji]0023,20e3[/emoji]");
						break;
					default:
						followSkip = 0;
						break;
					}
					skip += followSkip;
				}
				else
				{
					int followSkip = Character.charCount(followUnicode);
					switch (unicode)
					{
					case 0x1f1ef:
						if (followUnicode == 0x1f1f5)
							newText.append("[emoji]1f1ef,1f1f5[/emoji]");
						break;
					case 0x1f1fa:
						if (followUnicode == 0x1f1f8)
							newText.append("[emoji]1f1fa,1f1f8[/emoji]");
						break;
					case 0x1f1eb:
						if (followUnicode == 0x1f1f7)
							newText.append("[emoji]1f1eb,1f1f7[/emoji]");
						break;
					case 0x1f1e9:
						if (followUnicode == 0x1f1ea)
							newText.append("[emoji]1f1e9,1f1ea[/emoji]");
						break;
					case 0x1f1ee:
						if (followUnicode == 0x1f1f9)
							newText.append("[emoji]1f1ee,1f1f9[/emoji]");
						break;
					case 0x1f1ec:
						if (followUnicode == 0x1f1e7)
							newText.append("[emoji]1f1ec,1f1e7[/emoji]");
						break;
					case 0x1f1ea:
						if (followUnicode == 0x1f1f8)
							newText.append("[emoji]1f1ea,1f1f8[/emoji]");
						break;
					case 0x1f1f7:
						if (followUnicode == 0x1f1fa)
							newText.append("[emoji]1f1f7,1f1fa[/emoji]");
						break;
					case 0x1f1e8:
						if (followUnicode == 0x1f1f3)
							newText.append("[emoji]1f1e8,1f1f3[/emoji]");
						break;
					case 0x1f1f0:
						if (followUnicode == 0x1f1f7)
							newText.append("[emoji]1f1f0,1f1f7[/emoji]");
						break;
					default:
						{
							followSkip = 0;
							newText.append(charArr[i]);
							break;
						}
					}
					skip += followSkip;
				}
			}
			else
			{
				newText.append(charArr[i]);
			}
			// }
			/*
			 * else { newText.append(charArr[i]); }
			 */

		}
		return newText.toString();
	}
	
	public static final String newString(int codePoint)
	{
		if (Character.charCount(codePoint) == 1)
		{
			return String.valueOf((char)codePoint);
		}
		else
		{
			return new String(Character.toChars(codePoint));
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		String str = "a\ud83d\ude04bc\u0031\u20e3\ud83c\uddef\ud83c\uddf5\u2b07";
		System.out.println(str);
		System.out.println(str.codePointCount(0, str.length()));
		// 返回 0 1 3 4 5 6 7 9 11
		System.out.println(str.offsetByCodePoints(9, 1));
		System.out.println(str.codePointAt(6));// 0x1f604 代码点
		System.out.println(Integer.toHexString(128516));
		System.out.println("\u5f20\u6d77\u971e");
		System.out.println("\u0031\u20e3");
		System.out.println("\u2b07");
		System.out.println(Character.charCount(0x2b07));
		System.out.println("----------------------");
		System.out.println(str.codePointAt(11));// 0x2b07 代码点
		System.out.println(sEmojisMap.indexOfKey(0x1f53e));
		System.out.println("-----------------------------------");
		String text = "a\ud83d\ude04bc\u0031\u20e3\ud83c\uddef\ud83c\uddf5\u2b07你好啊。hdush你就急,，哈哈！";
		System.out.println(replaceEmoji(text));
		System.out.println(text);
		System.out.println("---------------------");
		String text2 = "a[emoji]1f604[/emoji]bc[emoji]0031,20e3[/emoji][emoji]1f1ef,1f1f5[/emoji][emoji]2b07[/emoji]你好啊。hdush你就急,，哈哈！";
		System.out.println(replaceUBB(text2));
		System.out.println(replaceUBBALL(text2));
		//System.out.println(text2.replaceAll("\\[emoji\\][0-9a-zA-Z]+,?[0-9a-zA-Z]*\\[/emoji\\]", "<表情>"));
		
		//System.out.println("----------------------------------------------------");
	//	String s = "1f604";
		//System.out.println(Integer.parseInt(s, 16));
		// Character.codep
		// System.out.println(Integer.toHexString(0x1f51f));
	}

}
