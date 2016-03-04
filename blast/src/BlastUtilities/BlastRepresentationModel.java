package BlastUtilities;

import RepresentationModels.AbstractModel;
import RepresentationModels.*;
import Utilities.RepresentationModel;

/**
 * @author stravanni
 */

public enum BlastRepresentationModel {
    CHARACTER_BIGRAMS,
    CHARACTER_BIGRAM_GRAPHS,
    CHARACTER_TRIGRAMS,
    CHARACTER_TRIGRAM_GRAPHS,
    CHARACTER_FOURGRAMS,
    CHARACTER_FOURGRAM_GRAPHS,
    TOKEN_UNIGRAMS,
    TOKEN_UNIGRAMS_ENTRO,
    TOKEN_UNIGRAM_GRAPHS,
    TOKEN_BIGRAMS,
    TOKEN_BIGRAM_GRAPHS,
    TOKEN_TRIGRAMS,
    TOKEN_TRIGRAM_GRAPHS;

    public static AbstractModel getModel(RepresentationModel model, String instanceName) {
        switch (model) {
            case CHARACTER_BIGRAMS:
                return new CharacterNGrams(2, model, instanceName);
            case CHARACTER_BIGRAM_GRAPHS:
                return new CharacterNGramGraphs(2, model, instanceName);
            case CHARACTER_FOURGRAMS:
                return new CharacterNGrams(4, model, instanceName);
            case CHARACTER_FOURGRAM_GRAPHS:
                return new CharacterNGramGraphs(4, model, instanceName);
            case CHARACTER_TRIGRAMS:
                return new CharacterNGrams(3, model, instanceName);
            case CHARACTER_TRIGRAM_GRAPHS:
                return new CharacterNGramGraphs(3, model, instanceName);
            case TOKEN_BIGRAMS:
                return new TokenNGrams(2, model, instanceName);
            case TOKEN_BIGRAM_GRAPHS:
                return new TokenNGramGraphs(2, model, instanceName);
            case TOKEN_TRIGRAMS:
                return new TokenNGrams(3, model, instanceName);
            case TOKEN_TRIGRAM_GRAPHS:
                return new TokenNGramGraphs(3, model, instanceName);
            case TOKEN_UNIGRAMS:
                return new TokenNGrams(1, model, instanceName);
//            case TOKEN_UNIGRAMS_ENTRO:
//                return new TokenNGrams_Entro(1, model, instanceName);
            case TOKEN_UNIGRAM_GRAPHS:
                return new TokenNGramGraphs(1, model, instanceName);
            default:
                return null;
        }
    }

    public static BlastUtilities.AbstractModel getModel(BlastRepresentationModel model, String instanceName) {
        switch (model) {
            case TOKEN_UNIGRAMS_ENTRO:
                return new TokenNGrams_Entro(1, model, instanceName);
            default:
                return null;
        }
    }
}